package com.newdjk.bdmember.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.MainActivity;
import com.newdjk.bdmember.MyApplication;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.bean.LoginEntity;
import com.newdjk.bdmember.bean.PrescriptionMessageEntity;
import com.newdjk.bdmember.bean.WXLoginInfo;
import com.newdjk.bdmember.bean.WXUserInfo;
import com.newdjk.bdmember.ui.activity.RegisterActivity;

import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.utils.MessageEvent;
import com.newdjk.bdmember.utils.MyCountDownTimer;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.utils.StrUtil;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.widget.MyCheckBox;
import com.newdjk.bdmember.widget.VerficationCodeView;
import com.newdjk.bdmember.wxapi.WXEntryActivity;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BasicActivity {
    @BindView(R.id.rg_tab)
    RadioGroup rg_tab;
    @BindView(R.id.rb_mmdl)
    RadioButton rb_mmdl;
    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_tx_code)
    EditText inputTxCode;
    @BindView(R.id.vcv_tx_code)
    VerficationCodeView vcvTxCode;
    @BindView(R.id.input_bd_code)
    EditText inputBdCode;
    @BindView(R.id.tv_bd_code)
    TextView tvBdCode;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.CheckBox)
    MyCheckBox CheckBox;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.relate_code_tx)
    RelativeLayout relateCodeTx;
    @BindView(R.id.relate_code_bd)
    RelativeLayout relateCodeBd;
    @BindView(R.id.relate_pwd)
    RelativeLayout relatePwd;

    private MyCountDownTimer mcdt;
    private String loginType = "2";//1 快速登录，2 密码登录
    private int index = 0;//判断发送验证码是否超过2次

    public static Intent getAct(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        super.init();
    }


    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        inputUser.setText(StrUtil.getString(SpUtils.getString(Contants.userName)));
        relateCodeTx.setVisibility(View.GONE);
        rb_mmdl.setChecked(true);
        relateCodeTx.setVisibility(View.GONE);
        relateCodeBd.setVisibility(View.GONE);
        relatePwd.setVisibility(View.VISIBLE);
        loginType = "2";
        CrashReport.testJavaCrash();

        addWindowManger(this);
    }

    @Override
    protected void initListener() {
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_ksdl://快速登录i
                        if (index < 2) {
                            relateCodeTx.setVisibility(View.GONE);
                        } else {
                            relateCodeTx.setVisibility(View.VISIBLE);
                        }
                        relateCodeBd.setVisibility(View.VISIBLE);
                        relatePwd.setVisibility(View.GONE);
                        loginType = "1";
                        break;
                    case R.id.rb_mmdl://密码登录
                        relateCodeTx.setVisibility(View.GONE);
                        relateCodeBd.setVisibility(View.GONE);
                        relatePwd.setVisibility(View.VISIBLE);
                        loginType = "2";
                        break;
                }
            }
        });
        CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });
    }

    @Override
    protected void initData() {
        mcdt = new MyCountDownTimer(tvBdCode, 60000, 1000);
        String mobile = (String) SpUtils.get(Contants.Mobile, "");
        String pwd = (String) SpUtils.get(Contants.inputPassword, "");

        inputUser.setText(mobile);
        inputPassword.setText(pwd);

//        EventBus.getDefault().post(new LoginEb(mobile, pwd));
    }

    @Override
    protected void otherViewClick(View view) {

    }

    /**
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MessageEvent event) {
        switch (event.getmType()) {
            case MainConstant.WXUSERINFO:
                final WXUserInfo userInfo = event.getUserInfo();
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("OpenId", userInfo.getOpenid());
                paramsMap.put("AppId", MainConstant.WEIXIN_APP_ID);
                mMyOkhttp.get().url(HttpUrl.QueryLoginAccount).params(paramsMap).tag(this).enqueue(new GsonResponseHandler<WXLoginInfo>() {
                    @Override
                    public void onSuccess(int statusCode, WXLoginInfo entituy) {
                        if (entituy.getData() == null) {
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            intent.putExtra("OpenId", userInfo.getOpenid());
                            startActivity(intent);
                        } else {
                            loading(true);
                            WXLoginInfo.DataBean data = entituy.getData();
                            Map<String, String> headMap = new HashMap<>();
                            headMap.put("Access-Client", "APP");
                            headMap.put("Access-RegistrationId", MyApplication.mRegistrationId);//极光推送的设备唯一性标识RegistrationID
                            headMap.put("Access-Platform", "Android");

                            Map<String, String> paramsMap = new HashMap<>();
                            paramsMap.put("Mobile", data.getMobile());
                            paramsMap.put("Type", "2");//登录类型:1验证码，2账号
                            paramsMap.put("Code", data.getPassword());
                            mMyOkhttp.post().url(HttpUrl.login).headers(headMap).params(paramsMap).tag(this).enqueue(new GsonResponseHandler<LoginEntity>() {
                                @Override
                                public void onSuccess(int statusCode, LoginEntity entituy) {
                                    LoadDialog.clear();
                                    vcvTxCode.refresh();
                                    if (entituy.getCode() == 0) {
                                        LoginEntity.DataEntity data = entituy.getData();
                                        myShared(data);
                                        myJpush();
                                        toActivity(MainActivity.getAct(mContext));
                                        noAnimFinish();
                                    }
                                    toast(entituy.getMessage());
                                }

                                @Override
                                public void onFailures(int statusCode, String errorMsg) {
                                    if (statusCode == 1001) {
                                        errorMsg = getString(R.string.accountOrPassError);
                                    }
                                    CommonMethod.requestError(statusCode, errorMsg);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailures(int statusCode, String errorMsg) {
                        CommonMethod.requestError(statusCode, errorMsg);
                    }
                });
                break;
        }

    }

    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
//        Access-Client 是 string app app端固定填写"APP"
//        注：此参数固定放在请求头里面
//        Access-RegistrationId 是 string zUxODI5ND 极光推送的设备唯一性标识RegistrationID
//        注：此参数固定放在请求头里面
//        Access-Platform 是 string Android APP平台 安卓固定填写"Android",苹果固定填写"IOS"
//        注：此参数固定放在请求头里面
//        Mobile 是 string 17773930653 手机号(登陆方式为账号时为账号)
//        Type 是 string 2 登录类型:1验证码，2账号
//        Code 是 string 123456 账号方式时为“密码”，验证码方式时为“验证码”

        if (loginType.equals("2") ? isNullPwd() : isNull()) {
            loading(true);
            Map<String, String> headMap = new HashMap<>();
            headMap.put("Access-Client", "APP");
            headMap.put("Access-RegistrationId", MyApplication.mRegistrationId);//极光推送的设备唯一性标识RegistrationID
            headMap.put("Access-Platform", "Android");

            Map<String, String> paramsMap = new HashMap<>();

            paramsMap.put("Mobile", StrUtil.getString(inputUser));
            paramsMap.put("Type", loginType);//登录类型:1验证码，2账号

            if ("1".equals(loginType)) {
                paramsMap.put("Code", StrUtil.getString(inputBdCode));
            } else {
                paramsMap.put("Code", StrUtil.getString(inputPassword));
            }
            mMyOkhttp.post().url(HttpUrl.login).headers(headMap).params(paramsMap).tag(this).enqueue(new GsonResponseHandler<LoginEntity>() {
                @Override
                public void onSuccess(int statusCode, LoginEntity entituy) {
                    LoadDialog.clear();
                    vcvTxCode.refresh();
                    if (entituy.getCode() == 0) {
                        LoginEntity.DataEntity data = entituy.getData();
                        myShared(data);
                        myJpush();
                        toActivity(MainActivity.getAct(mContext));
                    }
                    toast(getString(R.string.loginSuccess));
                }

                @Override
                public void onFailures(int statusCode, String errorMsg) {
                    if (statusCode == 1001) {
                        errorMsg = getString(R.string.accountOrPassError);
                    }
                    CommonMethod.requestError(statusCode, errorMsg);
                }
            });
        }
    }

    /**
     * 激光推送注册
     */
    private void myJpush() {

    }

    /**
     * 本地保存
     *
     * @param entituy
     */
    private void myShared(LoginEntity.DataEntity entituy) {
        Gson mGson = new Gson();
        PrescriptionMessageEntity prescriptionMessageEntity = new PrescriptionMessageEntity();
        prescriptionMessageEntity.setPatient(entituy);
        String mUserInfoGson = mGson.toJson(prescriptionMessageEntity);
        SpUtils.put(Contants.LoginJson, mUserInfoGson);
        SpUtils.put(Contants.Id, entituy.getData().getId());
        SpUtils.put(Contants.Mobile, entituy.getData().getMobile()); //登陆的手机号码
        SpUtils.put(Contants.Name, entituy.getData().getName());
        SpUtils.put(Contants.Sex, entituy.getData().getSex());
        SpUtils.put(Contants.Token, entituy.getToken());
        SpUtils.put(Contants.userName, StrUtil.getString(inputUser));
        SpUtils.put(Contants.inputPassword, StrUtil.getString(inputPassword)); //上一次登陆的密码保存在本地
        SpUtils.put(Contants.MUSERINFO, StrUtil.getString(mUserInfoGson)); //获取到的个人数据

        ((MyApplication) LoginActivity.this.getApplication()).setAccountId(entituy.getData().getId());
    }

    /**
     * 快速登录校验
     *
     * @return
     */
    private boolean isNull() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
            return false;
        } else if (index >= 2 ? vcvTxCode.isEqualsIgnoreCase(StrUtil.getString(inputTxCode)) : false) {
            toast(getString(R.string.randomError));
            return false;
        } else if (!StrUtil.isNotEmpty(inputBdCode, true)) {
            toast(getString(R.string.mobileCodeNull));
            return false;
        }
        return true;
    }

    /**
     * 密码登录校验
     *
     * @return
     */
    private boolean isNullPwd() {
        if (!StrUtil.isMobile(StrUtil.getString(inputUser))) {
            toast(getString(R.string.telError));
            return false;
        }
        if (!StrUtil.isNotEmpty(inputPassword, true)) {
            toast(getString(R.string.passEmpty));
            return false;
        }
        if (StrUtil.getString(inputPassword).length() < 6) {
            toast(getString(R.string.passLengthError));
            return false;
        }
        return true;
    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.tv_forget)
    public void onTvForgetClicked() {
        toActivity(ForgetActivity.getIntent(this));
    }

    /**
     * 注册
     */
    @OnClick(R.id.tv_register)
    public void onTvRegisterClicked() {
        toActivity(RegisterActivity.getAct(this));
    }

    /**
     * 刷新图形验证码
     */
    @OnClick(R.id.vcv_tx_code)
    public void onVcvTxCodeClicked() {
        vcvTxCode.refresh();
    }

    /**
     * 发送短信验证码
     */
    @OnClick(R.id.tv_bd_code)
    public void onBdCodeClicked() {
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


            mMyOkhttp.post().url(HttpUrl.LoginSendMobileCode).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entity) {
                    LoadDialog.clear();
                    if (entity.getCode() == 0) {
                        mcdt.start();
                        index++;
                    }
                }

                @Override
                public void onFailures(int statusCode, String errorMsg) {
                    CommonMethod.requestError(statusCode, errorMsg);
                }
            });
        } else {
            toast(getString(R.string.telError));
        }
    }


    /**
     * 微信登陸
     */
    @OnClick(R.id.linear_wx)
    public void onLinearWxClicked() {
        WXEntryActivity.loginWeixin(this, MyApplication.mWxApi);
    }


    /**
     * 双击退出登录
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), getString(R.string.exitApp),
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            killAll();
            System.exit(0);
        }
    }

    @Override
    public void addWindowManger(BasicActivity activity) {
        super.addWindowManger(activity);
    }
}
