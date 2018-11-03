package com.newdjk.bdmember.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.ui.adapter.HomeRecyclerViewFirstAdapter;
import com.newdjk.bdmember.utils.ItemOnClickCall;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BasicFragment implements ItemOnClickCall {


    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.et_patient_msg)
    TextView etPatientMsg;
    @BindView(R.id.relat_titlebar)
    RelativeLayout relatTitlebar;
    @BindView(R.id.liear_titlebar)
    RelativeLayout liearTitlebar;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.home_item_first_1)
    TextView homeItemFirst1;
    @BindView(R.id.home_item_first_2)
    TextView homeItemFirst2;
    @BindView(R.id.home_item_first_3)
    TextView homeItemFirst3;
    @BindView(R.id.home_item_first_4)
    TextView homeItemFirst4;
    @BindView(R.id.home_recyclerView)
    RecyclerView homeRecyclerView;
    Unbinder unbinder;

    @Override
    protected int initViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        initHomeRecyclerView();
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

    public static HomeFragment getFragment() {
        return SingletonHolder.instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void itemClickListener() {

    }

    private static class SingletonHolder {
        private static HomeFragment instance = new HomeFragment();
    }


    private void initHomeRecyclerView() {
        homeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        HomeRecyclerViewFirstAdapter adapter = new HomeRecyclerViewFirstAdapter(getContext(),this);
        homeRecyclerView.setAdapter(adapter);
    }

}
