package com.newdjk.bdmember.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.newdjk.bdmember.MainActivity;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.alipay.PayResult;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.AliPayInfo;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.wxapi.WXPayEntryActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OnlineFragment extends BasicFragment {
    private static final String TAG = "FunctionFragment";
    @BindView(R.id.mWebView)
    BridgeWebView mWebView;
    Unbinder unbinder;
    private String mUrl = HttpUrl.LOCAL_HOST_IP+"doctor-list?hide=1?";
    private CallBackFunction mfunction;
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        mfunction.onCallBack("success");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                        mfunction.onCallBack("fail");
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String mPayType;
    private Gson mGson;
    private String mUserInfo;


    public static OnlineFragment getFragment() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static OnlineFragment instance = new OnlineFragment();
    }


    @Override
    protected int initViewResId() {
        return R.layout.fragment_online;
    }

    @Override
    protected void initView() {
        mGson = new Gson();
        // 这里一定要加这个  不然会有缓存
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setTextZoom(100);  //消除系统大小的设置对webview字体大小的影响
        mWebView.getSettings().setDomStorageEnabled(true); //解决加载不出webview白屏问题
        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new BridgeWebViewClient(mWebView) {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LoadDialog.clear();
                sendDataToHtml();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
//      mWebView.loadUrl("file:///android_asset/index.html#/seekDoctor");
        mWebView.loadUrl(mUrl);
        initJsBridge();
    }

    private void sendDataToHtml() {
        String mUserInfo = SpUtils.getString(Contants.LoginJson);

        mWebView.callHandler("UserInfo", mUserInfo, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.d("onCallBack", "data=" + data);
            }
        });
    }

    private void initJsBridge() {
        // 注册一个方法给JS调用：关闭网页
        mWebView.registerHandler("Back", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.d("handler", "Back");
                // 关闭页面
                if (mWebView.canGoBack()) {
                    mWebView.canGoBack();
                } else {
                    ((MainActivity) getActivity()).toHomeFragment();
                }
            }
        });
        // 注册一个方法给JS调用：微信支付
        mWebView.registerHandler("pay", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.d("data==", data);
                mfunction = function;
                if ("1".equals(mPayType)) {
                    WXPayEntryActivity.weixinPay(data);
                } else {
                    AliPayInfo aliPayInfo = mGson.fromJson(data, AliPayInfo.class);
                    aliPay(aliPayInfo.getBody());
                }
            }
        });

        // 注册一个方法给JS调用：微信支付
        mWebView.registerHandler("payType", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.d("mPayType==", data);
                mPayType = data;
            }
        });


        // 患者支付成功之后跳转到ChatActivity页面的监听
        mWebView.registerHandler("ImInfo", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.d("ImInfo===", data);
//                Intent intent = new Intent(WebViewActivity.this, ChatActivity.class);
//
//                startActivity(intent);
            }
        });


    }


    public void aliPay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mUserInfo = (String) SpUtils.get(Contants.MUSERINFO, "");
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 返回页面的监听
     */
    public void canGoBackWebView() {
        // 关闭页面
        if (mWebView != null) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                ((MainActivity) getActivity()).exitNoTip();
            }
        } else {
            ((MainActivity) getActivity()).exitNoTip();
        }
    }
}
