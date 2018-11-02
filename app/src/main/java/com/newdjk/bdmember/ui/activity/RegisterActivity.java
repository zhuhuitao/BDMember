package com.newdjk.bdmember.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.bean.LoginEb;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.utils.MyCountDownTimer;
import com.newdjk.bdmember.utils.StrUtil;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.widget.MyCheckBox;
import com.newdjk.bdmember.widget.VerficationCodeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 注册
 */
public class RegisterActivity extends BasicActivity {
    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_tx_code)
    EditText inputTxCode;
    @BindView(R.id.vcv_tx_code)
    VerficationCodeView vcvTxCode;
    @BindView(R.id.relate_code_tx)
    RelativeLayout relateCodeTx;
    @BindView(R.id.input_bd_code)
    EditText inputBdCode;
    @BindView(R.id.tv_bd_code)
    TextView tvBdCode;
    @BindView(R.id.relate_code_bd)
    RelativeLayout relateCodeBd;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.CheckBox)
    MyCheckBox CheckBox;
    @BindView(R.id.cb_agreement)
    android.widget.CheckBox cbAgreement;
    private MyCountDownTimer mcdt;
    private int index = 0;//判断发送验证码是否超过2次
    private String openId;

    public static Intent getAct(Context context) {
        return new Intent(context, RegisterActivity.class);
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        initBackTitleBgRes(R.color.tm, "");
        relateCodeTx.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        CheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            openId = intent.getStringExtra("OpenId");
        }
        mcdt = new MyCountDownTimer(tvBdCode, 60000, 1000);
    }


    @Override
    protected void otherViewClick(View view) {

    }

    /**
     * 显示密码
     */
    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                //如果选中，显示密码
                inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //否则隐藏密码
                inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            inputPassword.setSelection(inputPassword.length());//将光标移至文字末尾
        }
    };


    /**
     * 用户协议
     */
    @OnClick(R.id.tv_agreement)
    public void onAgreementClicked() {
        //HttpUrl toActivity(AgreementActivity.getInten(mContext));
    }

    /**
     * 图形验证码
     */
    @OnClick(R.id.vcv_tx_code)
    public void onVcvTxCodeClicked() {
        vcvTxCode.refresh();
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
        if (StrUtil.isMobile(StrUtil.getString(inputUser))) {
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
        } else {
            toast("手机号码格式不正确");
        }
    }


    /**
     * 注册
     */
    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        requstVerifyMobileCode();
    }


    /**
     * 验证会员手机验证码(注册验证)
     */
    public void requstVerifyMobileCode() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
            return;
        } else if (!StrUtil.isNotEmpty(inputBdCode, true)) {
            toast(getString(R.string.mobileCodeNull));
            return;
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("Mobile", StrUtil.getString(inputUser));
            map.put("MobileCode", StrUtil.getString(inputBdCode));
            mMyOkhttp.post().url(HttpUrl.VerifyMobileCode).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entity) {
                    LoadDialog.clear();
                    if (entity.getCode() == 0) {
                        requstRegister();
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

    /**
     * 注册请求
     */
    public void requstRegister() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
            return;
        } else if (!StrUtil.isNotEmpty(inputBdCode, true)) {
            toast(getString(R.string.mobileCodeNull));
            return;
        } else if (!StrUtil.isNotEmpty(inputPassword, true)) {
            toast(getString(R.string.passEmpty));
            return;
        } else if (!cbAgreement.isChecked()) {
            toast(getString(R.string.readAgreement));
            return;
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            if (TextUtils.isEmpty(openId)) {
                map.put("Mobile", StrUtil.getString(inputUser));//                手机号
                map.put("PatientPassword", StrUtil.getString(inputPassword));//                密码
                map.put("MobileCode", StrUtil.getString(inputBdCode));//                验证码
                map.put("AppId", "newdjkpatapp");
                map.put("Source", "1");//
                mMyOkhttp.post().url(HttpUrl.RegistPatient).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                    @Override
                    public void onSuccess(int statusCode, Entity entituy) {
                        LoadDialog.clear();
                        vcvTxCode.refresh();
                        if (entituy.getCode() == 0) {
                            EventBus.getDefault().post(new LoginEb(StrUtil.getString(inputUser), StrUtil.getString(inputPassword)));
                            finish();
                        }
                    }

                    @Override
                    public void onFailures(int statusCode, String errorMsg) {
                        CommonMethod.requestError(statusCode, errorMsg);
                    }
                });
            } else {
                map.put("Mobile", StrUtil.getString(inputUser));//                手机号
                map.put("PatientPassword", StrUtil.getString(inputPassword));//                密码
                map.put("MobileCode", StrUtil.getString(inputBdCode));//                验证码
                map.put("AppId", MainConstant.WEIXIN_APP_ID);//                验证码
                map.put("OpenId", openId);//                验证码
                mMyOkhttp.post().url(HttpUrl.WechatRegist).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                    @Override
                    public void onSuccess(int statusCode, Entity entituy) {
                        LoadDialog.clear();
                        vcvTxCode.refresh();
                        if (entituy.getCode() == 0) {
                            EventBus.getDefault().post(new LoginEb(StrUtil.getString(inputUser), StrUtil.getString(inputPassword)));
                            finish();
                        }
                        toast(entituy.getMessage());
                    }

                    @Override
                    public void onFailures(int statusCode, String errorMsg) {
                        CommonMethod.requestError(statusCode, errorMsg);
                    }
                });
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mcdt != null) {
            mcdt.cancel();
        }
    }
}
