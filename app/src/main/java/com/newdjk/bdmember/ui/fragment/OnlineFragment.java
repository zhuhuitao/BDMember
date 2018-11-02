package com.newdjk.bdmember.ui.fragment;

import android.view.View;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;

public class OnlineFragment extends BasicFragment {
    @Override
    protected int initViewResId() {
        return R.layout.fragment_online;
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

    public static OnlineFragment getFragment() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static OnlineFragment instance = new OnlineFragment();
    }
}
