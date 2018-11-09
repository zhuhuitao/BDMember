package com.newdjk.bdmember.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.AreaEntity;
import com.newdjk.bdmember.bean.HealthHutEntity;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.ui.adapter.HealthHutAdapter;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.ContractRequestUtil;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.PopItemClickListener;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.HealthHubWindow;
import com.newdjk.bdmember.wxapi.WXEntryActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HealthFragment extends BasicFragment implements PopItemClickListener, RecyclerViewItemClickListener {


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
    Unbinder unbinder;


    private List<AreaEntity.DataBean> mAreaList;
    private HealthHubWindow mWindow;
    private List<HealthHutEntity.DataBean.ReturnDataBean> mHealthHubList;
    private HealthHutAdapter mHealthHubAdapter;
    private boolean mIsSelect;
    private int mIndex, mPage;
    private int mCurrentAreaId;


    @Override
    protected int initViewResId() {
        return R.layout.fragment_health;
    }

    @Override
    protected void initView() {
        tvLeft.setText(getString(R.string.selectArea));
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setTextColor(Color.WHITE);

        topTitle.setText(getString(R.string.healthHubThem));
        topTitle.setTextColor(Color.WHITE);


        mIndex = 20;
        mPage = 1;
        mCurrentAreaId = 0;

        initRecyclerView();
    }

    private void initRecyclerView() {
        mHealthHubList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHealth.setLayoutManager(layoutManager);
        mHealthHubAdapter = new HealthHutAdapter(mHealthHubList, getContext(), this);
        rvHealth.setAdapter(mHealthHubAdapter);
    }

    @Override
    protected void initListener() {
        tvLeft.setOnClickListener(v ->
                handlePopEvent());

        healthRefreshLayout.setOnRefreshListener(v -> {
            mPage = 1;
            doHealthHubActivitiesData(0, 0, mIndex, mPage);
            healthRefreshLayout.setEnableLoadMore(true);
        });

        healthRefreshLayout.setOnLoadMoreListener(v -> {
            mPage++;
            doHealthHubActivitiesData(0, mCurrentAreaId, mIndex, mPage);
        });
    }

    private void handlePopEvent() {
        mWindow.showAsDropDown(liearTitlebar, 0, 0);
    }

    @Override
    protected void initData() {
        getAreaData();
        doHealthHubActivitiesData(0, 0, mIndex, mPage);
    }

    private void getAreaData() {

        ContractRequestUtil.getInstance().getAreaData(new BaseCallback() {
            @Override
            public void success(Object o) {
                AreaEntity response = (AreaEntity) o;
                if (response.getCode() != 0) {
                    toast(response.getMessage());
                    return;
                }

                if (response.getData() != null) {
                    mAreaList = response.getData();
                    mWindow = new HealthHubWindow(getContext(), mAreaList);
                    mWindow.setItemClickListener(HealthFragment.this);
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

    public static HealthFragment getFragment() {
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
    public void popItemClickListener(int position, Object o) {
        if (mWindow.isShowing()) {
            mWindow.dismiss();
        }
        mIsSelect = true;
        AreaEntity.DataBean bean = (AreaEntity.DataBean) o;
        mCurrentAreaId = bean.getAreaId();
        doHealthHubActivitiesData(0, mCurrentAreaId, mIndex, mPage);
    }

    @Override
    public void recyclerViewClickListener(int position, Object o) {
        HealthHutEntity.DataBean.ReturnDataBean bean = (HealthHutEntity.DataBean.ReturnDataBean) o;
        int hubId = bean.getHealthHouseId();
        String userId = (String) SpUtils.get(Contants.Id, "");
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra("type", 20);
        intent.putExtra("code", Integer.parseInt(userId));
        intent.putExtra("id", hubId);
        toActivity(intent);

    }


    private static class SingletonHolder {
        private static HealthFragment instance = new HealthFragment();
    }


    private void doHealthHubActivitiesData(int orgId, int areaId, int index, int page) {

        HashMap<String, String> params = new HashMap<>();
        params.put("OrgId", orgId + "");
        params.put("AreaId", areaId + "");
        params.put("PageIndex", page + "");
        params.put("PageSize", index + "");
        mMyOkhttp.post().url(HttpUrl.GetHealthHouses).params(params).tag(this).enqueue(new GsonResponseHandler<HealthHutEntity>() {
            @Override
            public void onSuccess(int statusCode, HealthHutEntity response) {
                if (response.getCode() != 0) {
                    toast(response.getMessage());
                    return;
                }
                if (response.getData() != null) {
                    if (healthRefreshLayout.isRefreshing() || mIsSelect) {
                        mHealthHubList.clear();
                    }
                    mHealthHubList.addAll(response.getData().getReturnData());
                    mHealthHubAdapter.notifyDataSetChanged();
                    mIsSelect = false;
                    healthRefreshLayout.finishRefresh();
                    healthRefreshLayout.finishLoadMore();
                    if (mHealthHubList.size() < mIndex) {
                        healthRefreshLayout.setEnableLoadMore(false);
                    }
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                CommonMethod.requestError(statusCode, errorMsg);
                healthRefreshLayout.finishRefresh();
                healthRefreshLayout.finishLoadMore();
            }
        });
    }

}
