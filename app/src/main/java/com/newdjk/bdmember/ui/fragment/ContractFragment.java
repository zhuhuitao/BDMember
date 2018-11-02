package com.newdjk.bdmember.ui.fragment;

import android.view.View;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;

public class ContractFragment extends BasicFragment {
    @Override
    protected int initViewResId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initView() {

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

    public static ContractFragment getFragment() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static ContractFragment instance = new ContractFragment();
    }
}
