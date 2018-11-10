package com.newdjk.bdmember.ui.activity.contract;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.AreaEntity;
import com.newdjk.bdmember.bean.FamilyMedicalTeamEntity;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.ui.adapter.FamilyMedicalTeamAdapter;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.ContractRequestUtil;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.PopItemClickListener;
import com.newdjk.bdmember.utils.RecyclerViewItemGroupClickListener;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.HealthHubWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class FamilyMedicalTeam extends BasicActivity implements PopItemClickListener, RecyclerViewItemGroupClickListener {
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
    @BindView(R.id.tv_down)
    TextView tvDown;
    @BindView(R.id.rv_health)
    RecyclerView rvHealth;
    @BindView(R.id.health_refreshLayout)
    SmartRefreshLayout healthRefreshLayout;
    @BindView(R.id.tv_area_select)
    TextView tvAreaSelect;
    private List<AreaEntity.DataBean> mAreaList;

    private HealthHubWindow mWindow;
    private int mPage, mIndex, mServicePackageId, mOrgId, mAreaId;
    private List<FamilyMedicalTeamEntity.DataBean.ReturnDataBean> mFamilyMedicalTeamList;
    private FamilyMedicalTeamAdapter mAdapter;
    private boolean mIsSelect;

    @Override
    protected int initViewResId() {
        return R.layout.activity_family_medical_team;
    }

    @Override
    protected void initView() {
        mIndex = 20;
        mPage = 1;
        mServicePackageId = 63;
        mAreaId = 42;
        mOrgId = 0;
        initRv();
        topTitle.setText(getString(R.string.selectFamilyMedicalTeam));
        liearTitlebar.setBackgroundColor(Color.WHITE);
        topLeft.setVisibility(View.VISIBLE);
    }

    private void initRv() {
        mFamilyMedicalTeamList = new ArrayList<>();
        mAdapter = new FamilyMedicalTeamAdapter(this, mFamilyMedicalTeamList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHealth.setLayoutManager(manager);
        rvHealth.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        tvDown.setOnClickListener(v -> {
            if (mWindow != null) {
                if (mWindow.isShowing()) {
                    mWindow.dismiss();
                } else {
                    mWindow.showAsDropDown(tvAreaSelect, 0, 0);
                }
            }
        });

        healthRefreshLayout.setOnRefreshListener(v -> {
            mPage = 1;
            getFamilyData(mOrgId, mAreaId, mServicePackageId, mIndex, mPage);

        });

        healthRefreshLayout.setOnLoadMoreListener(v -> {
            mPage++;
            getFamilyData(mOrgId, mAreaId, mServicePackageId, mIndex, mPage);
        });

        topLeft.setOnClickListener(v -> this.finish());
    }

    @Override
    protected void initData() {
        getAreaData();
        getFamilyData(mOrgId, mAreaId, mServicePackageId, mIndex, mPage);
    }

    @Override
    protected void otherViewClick(View view) {

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
                    mWindow = new HealthHubWindow(FamilyMedicalTeam.this, mAreaList);
                    mWindow.setItemClickListener(FamilyMedicalTeam.this::popItemClickListener);
                }
            }

            @Override
            public void failed(int errorCode, String errorMsg) {
                CommonMethod.requestError(errorCode, errorMsg);
            }
        });
    }

    private void getFamilyData(int orgId, int areaId, int servicePackageId, int index, int page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("OrgId", orgId + "");
        params.put("ServicePageId", servicePackageId + "");
        params.put("AreaId", areaId + "");
        params.put("PageIndex", page + "");
        params.put("PageSize", index + "");
        mMyOkhttp.post().url(HttpUrl.GetFamilyDoctorTeams).params(params).tag(this).enqueue(new GsonResponseHandler<FamilyMedicalTeamEntity>() {
            @Override
            public void onSuccess(int statusCode, FamilyMedicalTeamEntity response) {
                if (response.getCode() != 0) {
                    toast(response.getMessage());
                    return;
                }
                if (response.getData() != null) {
                    if (healthRefreshLayout.isRefreshing() || mIsSelect) {
                        mFamilyMedicalTeamList.clear();
                        healthRefreshLayout.finishRefresh();
                    }

                    if (healthRefreshLayout.isEnableLoadMore()) {
                        healthRefreshLayout.finishLoadMore();
                    }

                    mFamilyMedicalTeamList.addAll(response.getData().getReturnData());
                    if (mFamilyMedicalTeamList.size() < mIndex) {
                        healthRefreshLayout.setEnableLoadMore(false);
                    }
                    mIsSelect = false;
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                CommonMethod.requestError(statusCode, errorMsg);
            }
        });
    }


    @Override
    public void popItemClickListener(int position, Object o) {
        if (mWindow != null && mWindow.isShowing()) {
            mWindow.dismiss();
        }
        mIsSelect = true;
        if (o != null) {
            getFamilyData(mOrgId, ((AreaEntity.DataBean) o).getAreaId(), mServicePackageId, mIndex, mPage);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWindow != null) {
            if (mWindow.isShowing()) {
                mWindow.dismiss();
                mWindow = null;
            }
        }
    }


    @Override
    public void recyclerViewChildrenClickListener(Object o) {
        if (o != null) {
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("type", 22);
            intent.putExtra("code", 1202);
            intent.putExtra("id", ((FamilyMedicalTeamEntity.DataBean.ReturnDataBean) o).getDrId());
            toActivity(intent);
        }
    }

    @Override
    public void recyclerViewItemClickListener(Object o) {
        int drId = ((FamilyMedicalTeamEntity.DataBean.ReturnDataBean) o).getDrId();
        Intent intent = new Intent(this, FillContactInformation.class);
        intent.putExtra("id", drId);
        toActivity(intent);
    }
}
