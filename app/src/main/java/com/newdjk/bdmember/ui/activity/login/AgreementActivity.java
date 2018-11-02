package com.newdjk.bdmember.ui.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;


/**
 * 用戶協議
 */
public class AgreementActivity extends BasicActivity {

    public static Intent getInten(Context context){
        return new Intent(context,AgreementActivity.class);
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initView() {
        initBackTitle("用户协议");
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
