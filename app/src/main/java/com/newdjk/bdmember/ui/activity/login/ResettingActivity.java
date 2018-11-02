package com.newdjk.bdmember.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.Entity;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.StrUtil;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.LoadDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 重置密码
 */
public class ResettingActivity extends BasicActivity {
    @BindView(R.id.input_Password)
    EditText inputPassword;
    @BindView(R.id.input_new_Password)
    EditText inputNewPassword;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;
    private String userId = "";//1 = 用户id

    public static Intent getIntent(Context context, String userId) {
        Intent intent = new Intent(context, ResettingActivity.class);
        intent.putExtra("userId", userId);
        return intent;
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_resetting;
    }

    @Override
    protected void initView() {
        initBackTitle(getString(R.string.forgetPass));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        userId = getIntent().getStringExtra("userId");
    }

    @Override
    protected void otherViewClick(View view) {

    }

    /**
     * 完成
     */
    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (isNull()) {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("Id", StrUtil.getString(userId));
            map.put("NewPass", StrUtil.getString(inputPassword));
            map.put("TwoPass", StrUtil.getString(inputNewPassword));
            mMyOkhttp.post().url(HttpUrl.ChangePatientAccountPassword).params(map).tag(this).enqueue(new GsonResponseHandler<Entity>() {
                @Override
                public void onSuccess(int statusCode, Entity entituy) {
                    LoadDialog.clear();
                    if (entituy.getCode() == 0) {
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

    public boolean isNull() {
        if (StrUtil.getString(inputPassword).length() < 6) {
            toast(getString(R.string.passLengthError));
            return false;
        } else if (!StrUtil.getString(inputPassword).equals(StrUtil.getString(inputNewPassword))) {
            toast(getString(R.string.tracePassError));
            return false;
        }
        return true;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
