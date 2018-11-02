package com.newdjk.bdmember.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.MyCountDownTimer;
import com.newdjk.bdmember.utils.StrUtil;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.widget.VerficationCodeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 忘记密码
 */
public class ForgetActivity extends BasicActivity {

    @BindView(R.id.input_user)
    EditText inputUser;

    @BindView(R.id.input_tx_code)
    EditText inputTxCode;
    @BindView(R.id.vcv_tx_code)
    VerficationCodeView vcvTxCode;

    @BindView(R.id.relate_code_tx)
    RelativeLayout relateCodeTx;
    @BindView(R.id.input_bd_code)
    EditText inputCode;
    @BindView(R.id.tv_bd_code)
    TextView tvCode;

    private MyCountDownTimer mcdt;
    private int index = 0;//判断发送验证码是否超过2次

    public static Intent getIntent(Context context) {
        return new Intent(context, ForgetActivity.class);
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initView() {
        initBackTitle(getString(R.string.forgetPass)).setTitleBgRes(R.color.white);
        relateCodeTx.setVisibility(View.GONE);
        mcdt = new MyCountDownTimer(tvCode, 60000, 1000);
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


    /**
     * 发送验证码
     */
    @OnClick(R.id.tv_bd_code)
    public void onCodeClicked() {
        if (index < 2) {
            requestCode();
        } else {
            relateCodeTx.setVisibility(View.VISIBLE);
            if (vcvTxCode.isEqualsIgnoreCase(StrUtil.getString(inputTxCode))) {
                requestCode();
            }
        }
    }

    /**
     * 发送验证码请求
     */
    public void requestCode() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("Mobile", StrUtil.getString(inputUser));
            map.put("MobileCode", "");
            mMyOkhttp.post().url(HttpUrl.SendMobileCode).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entity) {
                    LoadDialog.clear();
                    if (entity.getCode() == 0) {
                        mcdt.start();
                        index++;
                    }
                    toast(entity.getMessage());
                }

                @Override
                public void onFailures(int statusCode, String errorMsg) {
                    CommonMethod.requestError(statusCode, errorMsg);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mcdt != null) {
            mcdt.clear();
            mcdt.cancel();
        }
    }

    /**
     * 下一步
     */
    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
            return;
        } else if (!StrUtil.isNotEmpty(inputCode, true)) {
            toast(getString(R.string.enterCode));
            return;
        } else if (index >= 2 ? vcvTxCode.isEqualsIgnoreCase(StrUtil.getString(inputTxCode)) : false) {
            toast(getString(R.string.randomError));
            return;
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("Mobile", StrUtil.getString(inputUser));
            map.put("MobileCode", StrUtil.getString(inputCode));
            mMyOkhttp.post().url(HttpUrl.QueryPatientAccountByMobileCode).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entity) {
                    LoadDialog.clear();
                    if (entity.getCode() == 0) {
                        finish();
                        toActivity(ResettingActivity.getIntent(mContext, StrUtil.getString(entity.getData())));
                    } else {
                        toast(entity.getMessage());
                    }
                }

                @Override
                public void onFailures(int statusCode, String errorMsg) {
                    CommonMethod.requestError(statusCode, errorMsg);
                }
            });
        }
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mcdt != null) {
            mcdt.cancel();
        }
    }
}
