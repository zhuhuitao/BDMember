package com.newdjk.bdmember.ui.activity.contract;

import android.content.Intent;
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
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.ui.adapter.ServicePackageAdapter;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.ContractRequestUtil;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;
import com.newdjk.bdmember.widget.CommonMethod;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ServicePackageDetail extends BasicActivity implements RecyclerViewItemClickListener {
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
        mType = getIntent().getIntExtra("type",-1);
        if (mType == 1) {
            topTitle.setText(getString(R.string.specialPackage));
        } else if (mType == 2) {
            topTitle.setText(getString(R.string.baseServicePackage));
        }

        topTitle.setTextColor(getResources().getColor(R.color.deep_gray_text));
        liearTitlebar.setBackgroundColor(Color.WHITE);
        topLeft.setVisibility(View.VISIBLE);
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
        mAdapter = new ServicePackageAdapter(mList, this, this);
        rvHealth.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        healthRefreshLayout.setOnRefreshListener(v -> {
            mPage = 1;
            healthRefreshLayout.setEnableLoadMore(true);
            getServicePackageData();
        });

        healthRefreshLayout.setOnLoadMoreListener(v -> {
            mPage++;
            getServicePackageData();
        });

        topLeft.setOnClickListener(v -> this.finish());
    }

    @Override
    protected void initData() {

        getServicePackageData();
    }

    private void getServicePackageData() {
        ContractRequestUtil.getInstance().doHttpRequest(mPage, mIndex, mType + "", new BaseCallback() {
            @Override
            public void success(Object o) {
                ServicePackageEntity entity = (ServicePackageEntity) o;
                if (healthRefreshLayout.isRefreshing()) {
                    mList.clear();
                    healthRefreshLayout.finishRefresh();
                }
                if (healthRefreshLayout.isEnableLoadMore()) {
                    healthRefreshLayout.finishLoadMore();
                }
                mList.addAll(entity.getData().getReturnData());
                mAdapter.notifyDataSetChanged();
                if (mList.size() < mIndex) {
                    healthRefreshLayout.setEnableLoadMore(false);
                }
            }

            @Override
            public void failed(int errorCode, String errorMsg) {
                CommonMethod.requestError(errorCode, errorMsg);
            }
        });
    }

    @Override
    protected void otherViewClick(View view) {

    }

    @Override
    public void recyclerViewClickListener(int position, Object o) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("type", 21);
        intent.putExtra("code", 1202);
        ServicePackageEntity.DataBean.ReturnDataBean bean = (ServicePackageEntity.DataBean.ReturnDataBean) o;
        intent.putExtra("id", bean.getServicePackId());
        toActivity(intent);
    }
}
