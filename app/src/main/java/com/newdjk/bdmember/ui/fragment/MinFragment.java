package com.newdjk.bdmember.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.makeramen.roundedimageview.RoundedImageView;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicFragment;
import com.newdjk.bdmember.bean.PatientEntity;
import com.newdjk.bdmember.ui.activity.mine.PersonalDataActivity;
import com.newdjk.bdmember.ui.activity.mine.SystemSettingActivity;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.GlideMediaLoader;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.utils.MessageEvent;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心.
 */
public class MinFragment extends BasicFragment {
    @BindView(R.id.civImg)
    CircleImageView civImg;
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.liear_titlebar)
    LinearLayout liearTitlebar;
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.relat_titlebar)
    RelativeLayout relatTitlebar;
    @BindView(R.id.mOrderPic)
    LinearLayout mOrderPic;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static MinFragment getFragment() {
        return new MinFragment();
    }

    @Override
    protected int initViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        liearTitlebar.setBackgroundResource(R.color.theme);
        topTitle.setText("个人中心");
        topTitle.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        requestMineInfoData();
    }

    /**
     *  请求个人中心的数据
     */
    private void requestMineInfoData() {
        String id = (String) SpUtils.get(Contants.Id, "");
        String mUrl = HttpUrl.QueryPatientInfoByPatientId + "?PatientId=" +id;
        Log.d("url",mUrl);
        mMyOkhttp.get().url(mUrl).tag(this).enqueue(new GsonResponseHandler<PatientEntity>() {
            @Override
            public void onSuccess(int statusCode, PatientEntity entituy) {
                PatientEntity.DataBean data = entituy.getData();
                if (data != null) {
                    tvName.setText(data.getPatientName());
                    GlideMediaLoader.loadPhoto(mContext,civImg,data.getPicturePath());
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
            }
        });
    }

    @Override
    protected void otherViewClick(View view) {

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

    @OnClick({R.id.mOrderService,R.id.mOrderPre,R.id.mOrderContinuation,R.id.mOrderVideo,R.id.mOrderPic,R.id.myPrescription,R.id.mCase,R.id.mCoupons,R.id.mVisitPerson,R.id.mEvaluate,R.id.civImg, R.id.mSystemSetting, R.id.mAddressManager, R.id.myDortor,R.id.mFollowupPlan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civImg:
                toActivity(new Intent(getContext(), PersonalDataActivity.class));
                break;
            case R.id.mSystemSetting:
                toActivity(new Intent(getContext(), SystemSettingActivity.class));
                break;
            case R.id.mAddressManager:
                Intent mAddressIntent = new Intent(getContext(), WebViewActivity.class);
                mAddressIntent.putExtra("type", 12);
                toActivity(mAddressIntent);
                break;
            case R.id.myDortor:
                Intent myDoctorIntent = new Intent(getContext(), WebViewActivity.class);
                myDoctorIntent.putExtra("type", 6);
                toActivity(myDoctorIntent);
                break;
            case R.id.mFollowupPlan:
                Intent mFollowupPlanIntent = new Intent(getContext(), WebViewActivity.class);
                mFollowupPlanIntent.putExtra("type", 10);
                toActivity(mFollowupPlanIntent);
                break;
            case R.id.mEvaluate:
                Intent mEvaluateIntent = new Intent(getContext(), WebViewActivity.class);
                mEvaluateIntent.putExtra("type", 9);
                toActivity(mEvaluateIntent);
                break;
            case R.id.mVisitPerson:
                Intent mVisitPersonIntent = new Intent(getContext(), WebViewActivity.class);
                mVisitPersonIntent.putExtra("type", 7);
                toActivity(mVisitPersonIntent);
                break;
            case R.id.mCoupons:
                Intent mCouponsIntent = new Intent(getContext(), WebViewActivity.class);
                mCouponsIntent.putExtra("type", 11);
                toActivity(mCouponsIntent);
                break;
            case R.id.mCase:
                Intent mCaseIntent = new Intent(getContext(), WebViewActivity.class);
                mCaseIntent.putExtra("type", 13);
                toActivity(mCaseIntent);
                break;
            case R.id.myPrescription:
                Intent myPrescriptionIntent = new Intent(getContext(), WebViewActivity.class);
                myPrescriptionIntent.putExtra("type", 8);
                toActivity(myPrescriptionIntent);
                break;
            case R.id.mOrderPic:
                Intent mOrderPicIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderPicIntent.putExtra("type",1);
                toActivity(mOrderPicIntent);
                break;
            case R.id.mOrderVideo:
                Intent mOrderVideoIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderVideoIntent.putExtra("type",2);
                toActivity(mOrderVideoIntent);
                break;
            case R.id.mOrderContinuation:
                Intent mOrderContinuationIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderContinuationIntent.putExtra("type",3);
                toActivity(mOrderContinuationIntent);
                break;
            case R.id.mOrderPre:
                Intent mOrderPreIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderPreIntent.putExtra("type",4);
                toActivity(mOrderPreIntent);
                break;
            case R.id.mOrderService:
                Intent mOrderServiceIntent = new Intent(getContext(), WebViewActivity.class);
                mOrderServiceIntent.putExtra("type",5);
                toActivity(mOrderServiceIntent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getmType()) {
            case MainConstant.UPDATE_PHOTO:
                requestMineInfoData();
                break;
        }
    }

}
