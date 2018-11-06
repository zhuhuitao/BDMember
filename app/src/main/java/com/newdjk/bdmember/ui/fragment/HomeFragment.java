package com.newdjk.bdmember.ui.fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.AdBannerInfo;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.HealthGovernmentEntity;
import com.newdjk.bdmember.bean.PublicActivitiesEntity;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.ui.adapter.HomeHealthGovernmentAdapter;
import com.newdjk.bdmember.ui.adapter.HomePregnantMotherServicePackageAdapter;
import com.newdjk.bdmember.ui.adapter.HomePublicActivitiesAdapter;
import com.newdjk.bdmember.ui.adapter.HomeRecommendationFamousDoctorAdapter;
import com.newdjk.bdmember.ui.adapter.HomeRecyclerViewFirstAdapter;
import com.newdjk.bdmember.utils.GlideMediaLoader;
import com.newdjk.bdmember.utils.HomeItemClickListener;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.ItemOnClickCall;
import com.newdjk.bdmember.utils.LogUtils;
import com.newdjk.bdmember.utils.SystemUitl;
import com.newdjk.bdmember.widget.CommonMethod;
import com.newdjk.bdmember.widget.UpdateManage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BasicFragment implements ItemOnClickCall, HomeItemClickListener {


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
    @BindView(R.id.home_health_government_recyclerView)
    RecyclerView homeHealthGovernmentRecyclerView;

    private HomePregnantMotherServicePackageAdapter mHomePregnantMotherServicePackageAdapter;
    private HomeRecommendationFamousDoctorAdapter mHomeRecommendationFamousDoctorAdapter;
    private HomeRecommendationFamousDoctorAdapter mHomeRecommendationFamousNurseAdapter;
    private List<FamousDoctorOrNurseEntity.DataBean> mHomeRecommendationDoctorList, mHomeRecommendationNurseList;
    private List<PublicActivitiesEntity.DataBean.ReturnDataBean> mPublicActivitiesList;
    private HomePublicActivitiesAdapter mHomePublicActivitiesAdapter;
    private HomeHealthGovernmentAdapter mHomeHealthGovernmentAdapter;
    private List<HealthGovernmentEntity> mHealthGovernmentDateList;
    private List<AdBannerInfo.DataBean> mHotServiceDate;

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
        ivHomeSetMeal1.setOnClickListener(v -> jumpWebActivity(HomeItemClickListener.hot1));
        ivHomeSetMeal2.setOnClickListener(v -> jumpWebActivity(HomeItemClickListener.hot2));
        ivHomeSetMeal3.setOnClickListener(v -> jumpWebActivity(HomeItemClickListener.hot3));
        tvHomeRecommendationFamousDoctorMore.setOnClickListener(v -> jumpWebActivity(8));
        tvHomeRecommendationFamousNurseMore.setOnClickListener(v -> jumpWebActivity(9));
    }

    @Override
    protected void initData() {
        obtainAdvertiseDate();
        obtainHotServiceDate();
        obtainPublicActivitiesDate();
        obtainRecommendationFamousDoctorDate();
        obtainRecommendationFamousNurseDate();
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_APN_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE},1);
        UpdateManage manager = new UpdateManage(getContext(),"http://www.yihuan100.com/app/zhiyoukong/zhiyoukong.apk");
        manager.showDownloadDialog();
    }

    private void obtainPublicActivitiesDate() {
        HashMap<String, String> params = new HashMap<>();
        params.put("OrgId", "1");
        params.put("IsEnable", "1");
        params.put("PageIndex", "1");
        params.put("PageSize", "20");
        mMyOkhttp.post().url(HttpUrl.GetPublicWelfareActivitys).params(params).tag(this).enqueue(new GsonResponseHandler<PublicActivitiesEntity>() {
            @Override
            public void onSuccess(int statusCode, PublicActivitiesEntity response) {
                if (response.getCode() == 0) {
                    if (response.getData() != null) {
                        if (response.getData().getReturnData() != null) {
                            mPublicActivitiesList.clear();
                            mPublicActivitiesList.addAll(response.getData().getReturnData());
                            mHomePublicActivitiesAdapter.notifyDataSetChanged();
                        }
                    }

                } else {
                    toast(response.getMessage());
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                CommonMethod.requestError(statusCode, errorMsg);
            }
        });
    }

    private void obtainHotServiceDate() {
        String bannerUrl = HttpUrl.QueryAdBannerInfo + "?classId=" + 5 + "";
        mMyOkhttp.get().url(bannerUrl).tag(this).enqueue(new GsonResponseHandler<AdBannerInfo>() {
            @Override
            public void onSuccess(int statusCode, AdBannerInfo entituy) {
                List<AdBannerInfo.DataBean> data = entituy.getData();
                handHotServiceData(data);
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
            }
        });
    }

    private void handHotServiceData(List<AdBannerInfo.DataBean> data) {
        if (data != null && data.size() == 3) {
            mHotServiceDate = data;
            for (int i = 0; i < 3; i++) {
                if (i == 0)
                    Glide.with(getActivity()).load(data.get(i).getContent()).into(ivHomeSetMeal1);
                if (i == 1)
                    Glide.with(getActivity()).load(data.get(i).getContent()).into(ivHomeSetMeal2);
                if (i == 2)
                    Glide.with(getActivity()).load(data.get(i).getContent()).into(ivHomeSetMeal3);
            }

        } else {
            return;
        }

    }

    private void obtainAdvertiseDate() {
        String bannerUrl = HttpUrl.QueryAdBannerInfo + "?classId=" + 3 + "";
        mMyOkhttp.get().url(bannerUrl).tag(this).enqueue(new GsonResponseHandler<AdBannerInfo>() {
            @Override
            public void onSuccess(int statusCode, AdBannerInfo entity) {
                final List<AdBannerInfo.DataBean> data = entity.getData();
                if (data == null || data.size() == 0) return;
                List<String> images = new ArrayList<>();
                for (AdBannerInfo.DataBean datum : data) {
                    images.add(datum.getContent());
                }
                dealBannerData(images);
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
            }
        });
    }

    private void dealBannerData(List<String> images) {
        if (images == null || images.size() == 0) return;
        //设置banner样式
        //mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideMediaLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
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

    @Override
    public void homeItemListener(int type, Object obj) {

        switch (type) {
            case HomeItemClickListener.homePublicActivities:
                //LogUtils.e(type+"  "+obj.toString());
                toast(type + obj.toString());
                break;
            case HomeItemClickListener.homeNavigateSecond:
                jumpWebActivity(obj == null ? -1 : Integer.parseInt(obj.toString()));
                break;
            case HomeItemClickListener.homeFamousDoctor:
                FamousDoctorOrNurseEntity.DataBean bean = (FamousDoctorOrNurseEntity.DataBean) obj;
                jumpWebActivity(bean);
                break;

        }
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
        mHomeRecommendationFamousDoctorAdapter = new HomeRecommendationFamousDoctorAdapter(mHomeRecommendationDoctorList, this);
        mHomeRecommendationFamousDoctorAdapter.setmType(1);
        homeRecommendationFamousDoctorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        homeRecommendationFamousDoctorRecyclerView.setAdapter(mHomeRecommendationFamousDoctorAdapter);


        mHomeRecommendationNurseList = new ArrayList<>();
        mHomeRecommendationFamousNurseAdapter = new HomeRecommendationFamousDoctorAdapter(mHomeRecommendationNurseList, this);
        mHomeRecommendationFamousNurseAdapter.setmType(2);
        homeRecommendationFamousNurseRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        homeRecommendationFamousNurseRecyclerView.setAdapter(mHomeRecommendationFamousNurseAdapter);

        mPublicActivitiesList = new ArrayList<>();
        mHomePublicActivitiesAdapter = new HomePublicActivitiesAdapter(mPublicActivitiesList, getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePublicActivitiesRecyclerView.setLayoutManager(layoutManager);
        homePublicActivitiesRecyclerView.setAdapter(mHomePublicActivitiesAdapter);


        mHealthGovernmentDateList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HealthGovernmentEntity entity = new HealthGovernmentEntity();
            mHealthGovernmentDateList.add(entity);
        }
        LinearLayoutManager healthGovernment = new LinearLayoutManager(getContext());
        healthGovernment.setOrientation(LinearLayoutManager.VERTICAL);
        homeHealthGovernmentRecyclerView.setLayoutManager(healthGovernment);
        mHomeHealthGovernmentAdapter = new HomeHealthGovernmentAdapter(mHealthGovernmentDateList);
        homeHealthGovernmentRecyclerView.setAdapter(mHomeHealthGovernmentAdapter);

    }

    /**
     * des:0-7 homeRecyclerView click event
     * des 8-9 famous doctor and famous nurse more click event
     *
     * @param position
     */
    private void jumpWebActivity(int position) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);

        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                intent.putExtra("type", 15);
                intent.putExtra("code", 1101);
                break;
            case 3:
                intent.putExtra("type", 15);
                intent.putExtra("code", 1101);
                break;
            case 4:
                intent.putExtra("type", 16);
                intent.putExtra("code", 1201);
                break;
            case 5:
                break;
            case 6:
                intent.putExtra("type", 15);
                intent.putExtra("code", 1103);
                break;
            case 7:

                break;
            case 8:
                intent.putExtra("type", 15);
                break;
            case 9:
                intent.putExtra("type", 16);
                break;
            case 10:
                FamousDoctorOrNurseEntity.DataBean dataBean = mHomeRecommendationDoctorList.get(position);
                Intent mOrderVideoIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderVideoIntent.putExtra("type", 14);
                mOrderVideoIntent.putExtra("id", dataBean.getDrId());
                toActivity(mOrderVideoIntent);
                break;
            case 11:
                break;
            default:
                LogUtils.exception("the position is not right value!");
                break;
        }

        try {
            if (position != -1)
                toActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void jumpWebActivity(String tag) {
        String links = null;
        switch (tag) {
            case HomeItemClickListener.hot1:
                links = mHotServiceDate.get(0).getContentLink();
                break;
            case HomeItemClickListener.hot2:
                links = mHotServiceDate.get(1).getContentLink();
                break;
            case HomeItemClickListener.hot3:
                links = mHotServiceDate.get(2).getContentLink();
                break;
            default:
                break;
        }

        if (TextUtils.isEmpty(links)) {
            toast(getString(R.string.networkError));
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
            intent.setData(Uri.parse(links)); //为Intent设置DATA属性
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void jumpWebActivity(FamousDoctorOrNurseEntity.DataBean bean) {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra("type", 14);
        intent.putExtra("id", bean.getDrId());
        try {
            toActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }

}
