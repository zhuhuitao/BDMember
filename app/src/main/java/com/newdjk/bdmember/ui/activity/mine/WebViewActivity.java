package com.newdjk.bdmember.ui.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.AddressEntity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.bean.ImDoctorEntity;
import com.newdjk.bdmember.bean.PaintListEntity;
import com.newdjk.bdmember.bean.ServicePackageDetailEntity;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.ui.activity.ChatActivity;
import com.newdjk.bdmember.ui.activity.contract.FamilyMedicalTeam;
import com.newdjk.bdmember.ui.activity.contract.FillSocialSecurityInformation;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.ImageBase64;
import com.newdjk.bdmember.utils.LogUtils;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.SelectedPictureDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class WebViewActivity extends BasicActivity {


    private static final String TAG = "WebViewActivity";
    @BindView(R.id.mWebView)
    BridgeWebView mWebView;
    private String mUrl = "http://172.18.30.140/#/";
    private int type;
    private int id;
    private int code;
    private SelectedPictureDialog mSelectedPictureDialog;
    private CallBackFunction mFunction;
    private String mPicturePath;
    private final static int REQ_PERMISSION_CODE = 0x100;
    private Gson mGson;

    @Override
    protected int initViewResId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        mGson = new Gson();
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
            LogUtils.e(type + "dedddddd");
            id = intent.getIntExtra("id", 0);
            code = intent.getIntExtra("code", 0);
            switch (type) {
                case 1:
                    mUrl = mUrl + "img-order";
                    break;
                case 2:
                    mUrl = mUrl + "video-order";
                    break;
                case 3:
                    mUrl = mUrl + "continuation-order";
                    break;
                case 4:
                    mUrl = mUrl + "pre-order";
                    break;
                case 5:
                    mUrl = mUrl + "service-order";
                    break;
                case 6:
                    mUrl = mUrl + "my-doctor";
                    break;
                case 7:
                    mUrl = mUrl + "Patient-list";
                    break;
                case 8:
                    mUrl = mUrl + "prescription-list";
                    break;
                case 9:
                    mUrl = mUrl + "evaluate";
                    break;
                case 10:
                    mUrl = mUrl + "follow-up";
                    break;
                case 11:
                    mUrl = mUrl + "coupons";
                    break;
                case 12:
                    mUrl = mUrl + "address";
                    break;
                case 13:
                    mUrl = mUrl + "case";
                    break;
                case 14:
                    mUrl = mUrl + "doctor-index?id=" + id;
                    break;
                case 15:
                    if (code != 0) {
                        mUrl = mUrl + "doctor-list?code=" + code;
                    } else {
                        mUrl = mUrl + "doctor-list";
                    }
                    break;
                case 16:
                    if (code != 0) {
                        mUrl = mUrl + "nurse-list?code=" + code;
                    } else {
                        mUrl = mUrl + "nurse-list";
                    }
                    break;
                case 17:
                    mUrl = mUrl + "doctor-list?servicePackage=1";
                    break;
                case 18:
                    mUrl = mUrl + "nurse-list?servicePackage=1";
                    break;
                case 19:
                    mUrl = mUrl + "information";
                case 20:
                    mUrl = mUrl + "healthRoom/cabinDetails?id=" + id + "&userId=" + code;
                    break;
                case 21:
                    mUrl = mUrl + "signingPackage/details?ServicePackId=" + id;
                    break;
                case 22:
                    mUrl = mUrl + "signingPackage/groupDetails?drTeamId=" + id;
                    break;
                case 23:
                    mUrl = mUrl + "Patient-list?ChooseFor=" + id;
                    break;
                case 24:
                    mUrl = mUrl + "hospital";
                    break;
                case 25:
                    mUrl = mUrl +"address?chooseFor=1";
                    break;
            }

            LogUtils.e("========" + type);

        }
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
        mWebView.loadUrl(mUrl);
        initJsBridge();
        sendDataToHtml();
    }

    private void sendDataToHtml() {
        String mUserInfo = SpUtils.getString(Contants.LoginJson);
        Log.d("mUserInfo", mUserInfo);
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
                if (mWebView.canGoBack()) {
                    mWebView.goBack(); //goBack()表示返回WebView的上一页面
                } else {
                    finish();
                }
            }
        });

        mWebView.registerHandler("chooseAddress", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                AddressEntity.DataBean address = mGson.fromJson(data,AddressEntity.DataBean.class);
                EventBus.getDefault().postSticky(address);
                finish();
            }
        });

        mWebView.registerHandler("servicePackage", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (TextUtils.isEmpty(data)) return;
                ServicePackageDetailEntity entity = mGson.fromJson(data, ServicePackageDetailEntity.class);
                Intent intent = null;
                if (Contants.TYPE == 1){
                   // intent = new Intent(WebViewActivity.this,FillSocialSecurityInformation.class);
                }else if (Contants.TYPE == 2){
                    intent = new Intent(WebViewActivity.this, FamilyMedicalTeam.class);
                    intent.putExtra("packageDetailEntity", entity);
                }
                toActivity(intent);
                WebViewActivity.this.noAnimFinish();
            }
        });


        mWebView.registerHandler("choosePatient", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (data != null) {
                    PaintListEntity.DataBean info = mGson.fromJson(data, PaintListEntity.DataBean.class);
                    EventBus.getDefault().postSticky(info);
                }
            }
        });

        mWebView.registerHandler("APP", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (data != null && data.equals("Img")) {
                    mFunction = function;
                    mSelectedPictureDialog = new SelectedPictureDialog(WebViewActivity.this);
                    mSelectedPictureDialog.show();
                    mSelectedPictureDialog.setmOnPictrueClickListener(new SelectedPictureDialog.OnPictrueClickListener() {
                        @Override
                        public void onCamera() {
                            if (checkPermission()) {
                                gotoCamera();
                            }
                        }

                        @Override
                        public void onALbum() {
                            if (checkPermission()) {
                                gotoAlbum();
                            }
                        }
                    });
                }
            }
        });

        // 患者支付成功之后跳转到ChatActivity页面的监听
        mWebView.registerHandler("ImInfo", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.d("ImInfo===", data);

                Intent intent = new Intent(WebViewActivity.this, ChatActivity.class);
                ImDoctorEntity imDoctorEntity = mGson.fromJson(data, ImDoctorEntity.class);
                intent.putExtra("ImInfo", imDoctorEntity);
                startActivity(intent);
            }
        });


    }

    private void gotoAlbum() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    private void gotoCamera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String f = System.currentTimeMillis() + ".jpg";
        mPicturePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/UploadImage/" + f;
        File file = new File(mPicturePath);
        file.getParentFile().mkdirs();
        Log.i("zdp", mPicturePath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(mContext, "com.newdjk.member.file.provider", file);
        } else {
            uri = Uri.fromFile(file);
        }

        //添加权限
        openCameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(openCameraIntent, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION_CODE:
                for (int ret : grantResults) {
                    if (PackageManager.PERMISSION_GRANTED != ret) {
                        Toast.makeText(this, "用户没有允许需要的权限，使用可能会受到限制！", Toast.LENGTH_SHORT).show();
                        //  addLogMessage("用户没有允许需要的权限，使用可能会受到限制！");
                    }
                }
                break;
            default:
                break;
        }
    }


    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(WebViewActivity.this,
                        (String[]) permissions.toArray(new String[0]),
                        REQ_PERMISSION_CODE);
                return false;
            }
        }

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    uploadPicture(mPicturePath);
                    break;
                case 3:
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String path = cursor.getString(columnIndex);  //获取照片路径
                    uploadPicture(path);
                    break;
            }
        }
    }

    private void uploadPicture(String path) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String image64 = ImageBase64.bitmapToString(strings[0]);
                return image64;
            }

            @Override
            protected void onPostExecute(String s) {
                Map<String, String> map = new HashMap<>();
                map.put("Base64Str", s);
                mMyOkhttp.post().url(HttpUrl.ReportImageUpload).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                    @Override
                    public void onSuccess(int statusCode, Entity entituy) {
                        if (entituy.getCode() == 0) {
                            mFunction.onCallBack(entituy.getData().toString());
                        } else {
                            toast(entituy.getMessage());
                        }
                    }

                    @Override
                    public void onFailures(int statusCode, String errorMsg) {
                        CommonMethod.requestError(statusCode, errorMsg);
                    }
                });
            }
        }.execute(path);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebView.canGoBack()) {
                mWebView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                finish();
                return true;
            }
        }
        return false;
    }


    /**
     * 微信支付
     *
     * @paramdata
     */
    private void weixinPay(String data) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(this, MainConstant.WEIXIN_APP_ID);
        // 将该app注册到微信
        api.registerApp(MainConstant.WEIXIN_APP_ID);
        Log.d(TAG, "data=" + data);
        try {
            JSONObject json = new JSONObject(data);
            PayReq req = new PayReq();
            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = json.getString("package");
            req.sign = json.getString("sign");
            req.extData = "app data"; // optional
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
