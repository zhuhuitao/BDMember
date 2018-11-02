package com.newdjk.bdmember.ui.activity.mine;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.ui.activity.login.LoginActivity;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.MyCountDownTimer;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.utils.StrUtil;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;
import com.newdjk.bdmember.widget.VerficationCodeView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyMobileActivity extends BasicActivity {


    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.relat_titlebar)
    RelativeLayout relatTitlebar;
    @BindView(R.id.liear_titlebar)
    LinearLayout liearTitlebar;
    @BindView(R.id.mOldMobile)
    TextView mOldMobile;
    @BindView(R.id.mNewMobile)
    EditText mNewMobile;
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
    @BindView(R.id.mConfirmPassword)
    EditText mConfirmPassword;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;


    private MyCountDownTimer mcdt;
    private int index = 0;//判断发送验证码是否超过2次

    @Override
    protected int initViewResId() {
        return R.layout.activity_modify_mobile;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            String mobile = intent.getStringExtra("mobile");
            mOldMobile.setText(mobile);
        }
        initTitle("修改手机号码").setLeftImage(R.mipmap.head_back_n).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleBgRes(R.color.white);
        relateCodeTx.setVisibility(View.GONE);
        mcdt = new MyCountDownTimer(tvBdCode, 60000, 1000);


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
        if (!StrUtil.isMobile(StrUtil.getString(mNewMobile))) {
            toast("手机号码格式不正确");
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("Mobile", StrUtil.getString(mNewMobile));
            map.put("MobileCode", "");
            mMyOkhttp.post().url(HttpUrl.SendMobileCode).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
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
        }
    }

    /**
     * 下一步
     */
    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {
        if (!StrUtil.isMobile(StrUtil.getString(mNewMobile))) {
            toast("手机号码格式不正确");
            return;
        } else if (!StrUtil.isNotEmpty(inputBdCode, true)) {
            toast("验证码不能为空");
            return;
        } else if (index >= 2 ? vcvTxCode.isEqualsIgnoreCase(StrUtil.getString(inputTxCode)) : false) {
            toast("图形验证码不正确");
            return;
        } else if (!StrUtil.isNotEmpty(inputPassword,true)) {
            toast("请输入密码");
        } else  if (!StrUtil.isNotEmpty(mConfirmPassword,true)) {
            toast("请再次输入密码");
        } else if (!StrUtil.getString(inputPassword).equals(StrUtil.getString(mConfirmPassword))) {
            toast("两次输入的密码不一致");
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("AccountId", StrUtil.getString(SpUtils.get(Contants.Id,"")));
            map.put("Mobile", StrUtil.getString(mNewMobile));
            map.put("MobileCode", StrUtil.getString(inputBdCode));
            map.put("NewPass", StrUtil.getString(inputPassword));
            map.put("TwoPass", StrUtil.getString(mConfirmPassword));
            mMyOkhttp.post().url(HttpUrl.ModifyMobile).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entity) {
                    LoadDialog.clear();
                    if (entity.getCode() == 0) {
                        String userName = mNewMobile.getText().toString();
                        SpUtils.put(Contants.userName,userName);
                        SpUtils.put(Contants.inputPassword,"");
                        startActivity(new Intent(ModifyMobileActivity.this, LoginActivity.class));
                        finish();
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
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

}
