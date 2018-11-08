package com.newdjk.bdmember.ui.activity.contract;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.ui.adapter.ServicePackageAdapter;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.ContractRequestUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicePackageDetail extends BasicActivity {
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
    @BindView(R.id.rv_health)
    RecyclerView rvHealth;
    @BindView(R.id.health_refreshLayout)
    SmartRefreshLayout healthRefreshLayout;
    private int mType;
    private int mPage, mIndex;
    private List<ServicePackageEntity.DataBean.ReturnDataBean> mList;
    private ServicePackageAdapter mAdapter;

    @Override
    protected int initViewResId() {
        return R.layout.activity_service_package_detail;
    }

    @Override
    protected void initView() {
        topTitle.setText(getString(R.string.baseServicePackage));
        topTitle.setTextColor(Color.WHITE);

        mType = getIntent().getIntExtra("type", -1);
        mPage = 1;
        mIndex = 20;

        initRv();
    }

    private void initRv() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHealth.setLayoutManager(manager);

        mList = new ArrayList<>();
        mList.add(new ServicePackageEntity.DataBean.ReturnDataBean());
        mList.add(new ServicePackageEntity.DataBean.ReturnDataBean());
        mList.add(new ServicePackageEntity.DataBean.ReturnDataBean());
        mList.add(new ServicePackageEntity.DataBean.ReturnDataBean());
        mAdapter = new ServicePackageAdapter(mList,this);
        rvHealth.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        getServicePackageData();
    }

    private void getServicePackageData() {
        ContractRequestUtil.getInstance().doHttpRequest(mPage, mIndex, mType + "", new BaseCallback() {
            @Override
            public void success(Object o) {

            }

            @Override
            public void failed(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    protected void otherViewClick(View view) {

    }

}
