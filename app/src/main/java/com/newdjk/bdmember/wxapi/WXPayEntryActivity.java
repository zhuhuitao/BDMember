package com.newdjk.bdmember.wxapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newdjk.bdmember.MyApplication;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.utils.MessageEvent;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String WEIXIN_ACCESS_TOKEN_KEY = "wx_access_token_key";
    private static final String WEIXIN_OPENID_KEY = "wx_openid_key";
    private static final String WEIXIN_REFRESH_TOKEN_KEY = "wx_refresh_token_key";
    private static final String TAG = "WXPayEntryActivity";

    private Gson mGson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 微信事件回调接口注册
        MyApplication.mWxApi.handleIntent(getIntent(), this);
        mGson = new Gson();
    }

    /**
     * 微信组件注册初始化
     *
     * @param context       上下文
     * @param weixin_app_id appid
     * @return 微信组件api对象
     */
    public static IWXAPI initWeiXin(Context context, @NonNull String weixin_app_id) {
        if (TextUtils.isEmpty(weixin_app_id)) {
            Toast.makeText(context.getApplicationContext(), "app_id 不能为空", Toast.LENGTH_SHORT).show();
        }
        IWXAPI api = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        api.registerApp(weixin_app_id);
        return api;
    }



    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    /**
     * 微信支付
     * @param data
     */
    public static void weixinPay(String data) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(MyApplication.getContext(), MainConstant.WEIXIN_APP_ID,true);
        // 将该app注册到微信
        api.registerApp(MainConstant.WEIXIN_APP_ID);
        Log.d(TAG,"data=" + data);
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


    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        if(resp.getType()==ConstantsAPI.COMMAND_PAY_BY_WX){
            MessageEvent event = new MessageEvent();
            event.setmType(MainConstant.WXPAYRESULT);
            if(resp.errCode==0){
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                event.setPayCode(0);
            }
            else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                event.setPayCode(1);
            }
            EventBus.getDefault().post(event);
            finish();
        }

    }

}
