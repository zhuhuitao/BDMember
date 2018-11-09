package com.newdjk.bdmember.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.ui.activity.contract.FamilyMedicalTeam;
import com.newdjk.bdmember.ui.activity.contract.ServicePackageDetail;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.ui.adapter.ContractServicePackageAdapter;
import com.newdjk.bdmember.utils.BaseCallback;
import com.newdjk.bdmember.utils.ContractRequestUtil;
import com.newdjk.bdmember.utils.HomeItemClickListener;
import com.newdjk.bdmember.widget.CommonMethod;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContractFragment extends BasicFragment implements HomeItemClickListener {
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
    @BindView(R.id.contract_banner)
    Banner contractBanner;
    Unbinder unbinder;
    @BindView(R.id.contract_base_service_package)
    RelativeLayout contractBaseServicePackage;
    @BindView(R.id.contract_base_service_package_rv)
    RecyclerView contractBaseServicePackageRv;
    @BindView(R.id.contract_special_service_package)
    RelativeLayout contractSpecialServicePackage;
    @BindView(R.id.contract_special_service_package_rv)
    RecyclerView contractSpecialServicePackageRv;
    private ContractServicePackageAdapter mBasePackageAdapter, mSpecialPackageAdapter;
    private List<ServicePackageEntity.DataBean.ReturnDataBean> mBasePackageList, mSpecialPackageList;
    private int mPages, mIndex;

    @Override
    protected int initViewResId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initView() {
        topTitle.setText(R.string.contractTextThem);
        topTitle.setTextColor(getResources().getColor(R.color.white));
        mPages = 1;
        mIndex = 3;
        initRecyclerView();

    }

    @Override
    protected void initListener() {
        contractBaseServicePackage.setOnClickListener(v ->
              //  jumpToServicePackageDetail(2)
                toActivity(new Intent(getContext(),FamilyMedicalTeam.class))
        );

        contractSpecialServicePackage.setOnClickListener(v ->
                jumpToServicePackageDetail(1)
        );
    }

    private void jumpToServicePackageDetail(int type) {
        Intent intent = new Intent(getContext(), ServicePackageDetail.class);
        intent.putExtra("type", type);
        toActivity(intent);
    }

    @Override
    protected void initData() {
        getBasePackageData();
        getSpecialPackageData();
    }

    private void getSpecialPackageData() {
        ContractRequestUtil.getInstance().doHttpRequest(mPages, mIndex, "1", new BaseCallback() {
            @Override
            public void success(Object o) {
                ServicePackageEntity entity = (ServicePackageEntity) o;
                if (entity == null) return;
                if (entity.getCode() == 0) {
                    mSpecialPackageList.clear();
                    if (entity.getData().getReturnData() != null) {
                        mSpecialPackageList.addAll(entity.getData().getReturnData());
                        mSpecialPackageAdapter.notifyDataSetChanged();
                    }

                } else {
                    toast(entity.getMessage());
                }
            }

            @Override
            public void failed(int errorCode, String errorMsg) {
                CommonMethod.requestError(errorCode, errorMsg);
            }
        });
    }

    private void getBasePackageData() {
        ContractRequestUtil.getInstance().doHttpRequest(mPages, mIndex, "2", new BaseCallback() {
            @Override
            public void success(Object o) {
                ServicePackageEntity entity = (ServicePackageEntity) o;
                if (entity == null) return;
                if (entity.getCode() == 0) {
                    mBasePackageList.clear();
                    if (entity.getData().getReturnData() != null) {
                        mBasePackageList.addAll(entity.getData().getReturnData());
                        mBasePackageAdapter.notifyDataSetChanged();
                    }
                } else {
                    toast(entity.getMessage());

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

    public static ContractFragment getFragment() {
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
    public void homeItemListener(int type, Object obj) {
        ServicePackageEntity.DataBean.ReturnDataBean bean = (ServicePackageEntity.DataBean.ReturnDataBean) obj;
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        intent.putExtra("type", 21);
        intent.putExtra("code", 1202);
        intent.putExtra("id", bean.getServicePackId());
        toActivity(intent);
    }

    private static class SingletonHolder {
        private static ContractFragment instance = new ContractFragment();
    }

    private void initRecyclerView() {
        mBasePackageList = new ArrayList<>();
        mBasePackageAdapter = new ContractServicePackageAdapter(mBasePackageList, this);
        contractBaseServicePackageRv.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        contractBaseServicePackageRv.setAdapter(mBasePackageAdapter);

        mSpecialPackageList = new ArrayList<>();
        mSpecialPackageAdapter = new ContractServicePackageAdapter(mSpecialPackageList, this);
        contractSpecialServicePackageRv.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        contractSpecialServicePackageRv.setAdapter(mSpecialPackageAdapter);
    }
}
