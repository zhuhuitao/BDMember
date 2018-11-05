package com.newdjk.bdmember.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.PublicActivitiesEntity;
import com.newdjk.bdmember.ui.adapter.HomePregnantMotherServicePackageAdapter;
import com.newdjk.bdmember.ui.adapter.HomePublicActivitiesAdapter;
import com.newdjk.bdmember.ui.adapter.HomeRecommendationFamousDoctorAdapter;
import com.newdjk.bdmember.ui.adapter.HomeRecyclerViewFirstAdapter;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.ItemOnClickCall;
import com.newdjk.bdmember.utils.LogUtils;
import com.newdjk.bdmember.widget.CommonMethod;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

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
    @BindView(R.id.banner_first)
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
    @BindView(R.id.iv_home_long_range_remote)
    ImageView ivHomeLongRangeRemote;
    @BindView(R.id.iv_home_set_meal_1)
    ImageView ivHomeSetMeal1;
    @BindView(R.id.iv_home_set_meal_2)
    ImageView ivHomeSetMeal2;
    @BindView(R.id.iv_home_set_meal_3)
    ImageView ivHomeSetMeal3;
    @BindView(R.id.home_pregnant_mother_service_package_recyclerView)
    RecyclerView homePregnantMotherServicePackageRecyclerView;
    @BindView(R.id.tv_home_recommendation_famous_doctor_more)
    TextView tvHomeRecommendationFamousDoctorMore;
    @BindView(R.id.home_recommendation_famous_doctor_recyclerView)
    RecyclerView homeRecommendationFamousDoctorRecyclerView;
    @BindView(R.id.home_smart_refresh_layout)
    SmartRefreshLayout homeSmartRefreshLayout;
    @BindView(R.id.tv_home_recommendation_famous_nurse_more)
    TextView tvHomeRecommendationFamousNurseMore;
    @BindView(R.id.home_recommendation_famous_nurse_recyclerView)
    RecyclerView homeRecommendationFamousNurseRecyclerView;
    @BindView(R.id.tv_home_public_activities)
    TextView tvHomePublicActivities;
    @BindView(R.id.home_public_activities_recyclerView)
    RecyclerView homePublicActivitiesRecyclerView;

    private HomePregnantMotherServicePackageAdapter mHomePregnantMotherServicePackageAdapter;
    private HomeRecommendationFamousDoctorAdapter mHomeRecommendationFamousDoctorAdapter;
    private HomeRecommendationFamousDoctorAdapter mHomeRecommendationFamousNurseAdapter;
    private List<FamousDoctorOrNurseEntity.DataBean> mHomeRecommendationDoctorList, mHomeRecommendationNurseList;
    private List<PublicActivitiesEntity> mPulicActivitiesList;
    private HomePublicActivitiesAdapter mHomePublicActivitiesAdapter;

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
        obtainRecommendationFamousDoctorDate();
        obtainRecommendationFamousNurseDate();
    }

    private void obtainRecommendationFamousNurseDate() {
        String path = HttpUrl.QueryDoctorInfoForHot + "?HosGroupId=" + 0 + "&OrgId=" + 0 + "&DrType=" + 1;
        mMyOkhttp.get().url(path).tag(this).enqueue(new GsonResponseHandler<FamousDoctorOrNurseEntity>() {
            @Override
            public void onSuccess(int statusCode, FamousDoctorOrNurseEntity entituy) {
                if (homeSmartRefreshLayout.isRefreshing())
                    homeSmartRefreshLayout.setEnableRefresh(false);
                List<FamousDoctorOrNurseEntity.DataBean> data = entituy.getData();
                if (data == null || data.size() == 0) {
                    // mDoctorContainer.setVisibility(View.GONE);
                } else {
                    //  mDoctorContainer.setVisibility(View.VISIBLE);
                    if (data.size() > 6) {
                        data = data.subList(0, 6);
                    }
                    LogUtils.e(data.toString());
                    mHomeRecommendationNurseList.addAll(data);
                    mHomeRecommendationFamousNurseAdapter.setNewData(mHomeRecommendationNurseList);
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                if (homeSmartRefreshLayout.isRefreshing()) {
                    homeSmartRefreshLayout.setEnableRefresh(false);
                }
                CommonMethod.requestError(statusCode, errorMsg);
            }
        });
    }

    private void obtainRecommendationFamousDoctorDate() {
        String path = HttpUrl.QueryDoctorInfoForHot + "?HosGroupId=" + 0 + "&OrgId=" + 0 + "&DrType=" + 1;
        mMyOkhttp.get().url(path).tag(this).enqueue(new GsonResponseHandler<FamousDoctorOrNurseEntity>() {
            @Override
            public void onSuccess(int statusCode, FamousDoctorOrNurseEntity entituy) {
                if (homeSmartRefreshLayout.isRefreshing())
                    homeSmartRefreshLayout.setEnableRefresh(false);
                List<FamousDoctorOrNurseEntity.DataBean> data = entituy.getData();
                if (data == null || data.size() == 0) {
                    // mDoctorContainer.setVisibility(View.GONE);
                } else {
                    //  mDoctorContainer.setVisibility(View.VISIBLE);
                    if (data.size() > 6) {
                        data = data.subList(0, 6);
                    }
                    LogUtils.e(data.toString());
                    mHomeRecommendationDoctorList = data;
                    mHomeRecommendationFamousDoctorAdapter.setNewData(mHomeRecommendationDoctorList);
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                if (homeSmartRefreshLayout.isRefreshing()) {
                    homeSmartRefreshLayout.setEnableRefresh(false);
                }
                CommonMethod.requestError(statusCode, errorMsg);
            }
        });
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
        homeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        HomeRecyclerViewFirstAdapter adapter = new HomeRecyclerViewFirstAdapter(getContext(), this);
        homeRecyclerView.setAdapter(adapter);

        homePregnantMotherServicePackageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_divider));
        homePregnantMotherServicePackageRecyclerView.addItemDecoration(divider);
        mHomePregnantMotherServicePackageAdapter = new HomePregnantMotherServicePackageAdapter(new ArrayList<>());
        homePregnantMotherServicePackageRecyclerView.setAdapter(mHomePregnantMotherServicePackageAdapter);

        mHomeRecommendationDoctorList = new ArrayList<>();
        mHomeRecommendationFamousDoctorAdapter = new HomeRecommendationFamousDoctorAdapter(mHomeRecommendationDoctorList);
        mHomeRecommendationFamousDoctorAdapter.setmType(1);
        homeRecommendationFamousDoctorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        homeRecommendationFamousDoctorRecyclerView.setAdapter(mHomeRecommendationFamousDoctorAdapter);


        mHomeRecommendationNurseList = new ArrayList<>();
        mHomeRecommendationFamousNurseAdapter = new HomeRecommendationFamousDoctorAdapter(mHomeRecommendationNurseList);
        mHomeRecommendationFamousNurseAdapter.setmType(2);
        homeRecommendationFamousNurseRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        homeRecommendationFamousNurseRecyclerView.setAdapter(mHomeRecommendationFamousNurseAdapter);

        mPulicActivitiesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mPulicActivitiesList.add(new PublicActivitiesEntity());
        }
        mHomePublicActivitiesAdapter = new HomePublicActivitiesAdapter(mPulicActivitiesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        DividerItemDecoration divider1 = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider1.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_divider));
        homePublicActivitiesRecyclerView.setLayoutManager(layoutManager);
        homePublicActivitiesRecyclerView.addItemDecoration(divider1);
        homePublicActivitiesRecyclerView.setAdapter(mHomePublicActivitiesAdapter);

    }

}
