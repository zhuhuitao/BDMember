package com.newdjk.bdmember.ui.activity.contract;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.FamilyMedicalDetailInfo;
import com.newdjk.bdmember.bean.PaintListEntity;
import com.newdjk.bdmember.ui.activity.mine.WebViewActivity;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.GlidUtil;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.widget.CircleImageView;
import com.newdjk.bdmember.wxapi.WXEntryActivity;

import java.util.List;

import butterknife.BindView;


public class FillContactInformation extends BasicActivity {
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
    @BindView(R.id.iv_user_header)
    CircleImageView ivUserHeader;
    @BindView(R.id.tv_contract_name)
    TextView tvContractName;
    @BindView(R.id.tv_contact_id_card)
    TextView tvContactIdCard;
    @BindView(R.id.tv_contract_tel)
    TextView tvContractTel;
    @BindView(R.id.tv_change_other)
    TextView tvChangeOther;
    @BindView(R.id.tv_hometown_address)
    TextView tvHometownAddress;
    @BindView(R.id.tv_address_select)
    TextView tvAddressSelect;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_social_security)
    TextView tvSocialSecurity;
    @BindView(R.id.tv_social_security_select)
    TextView tvSocialSecuritySelect;
    @BindView(R.id.iv_doctor_header)
    CircleImageView ivDoctorHeader;
    @BindView(R.id.tv_doctor_info)
    TextView tvDoctorInfo;
    @BindView(R.id.tv_contract_team_name)
    TextView tvContractTeamName;
    @BindView(R.id.tv_team_type)
    TextView tvTeamType;
    @BindView(R.id.tv_contract_service_package_select)
    TextView tvContractServicePackageSelect;
    @BindView(R.id.tv_contract_agreement)
    TextView tvContractAgreement;
    @BindView(R.id.bt_confirm_submit)
    AppCompatButton btConfirmSubmit;


    private List<PaintListEntity.DataBean> mPaintList;
    private int mDrId;
    private FamilyMedicalDetailInfo.DataBean mFamilyMedicalDetailInfo;

    @Override
    protected int initViewResId() {
        return R.layout.activity_fill_contact_information;
    }

    @Override
    protected void initView() {
        mDrId = getIntent().getIntExtra("id", -1);
        topTitle.setText(getString(R.string.fillContractInfo));
        liearTitlebar.setBackgroundColor(Color.WHITE);
        topLeft.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
        topLeft.setOnClickListener(v -> this.finish());
        tvChangeOther.setOnClickListener(v->{
            Intent intent = new Intent(this,WebViewActivity.class);
            //intent.putExtra("type",)
        });
    }

    @Override
    protected void initData() {
        obtainPaintList();
        obtainFamilyMedicalTeamDetail();
    }

    private void obtainFamilyMedicalTeamDetail() {
        if (mDrId == -1) {
            return;
        }

        String url = HttpUrl.GetFamilyDoctorTeamDetail + "?drTeamId=" + 1;
        mMyOkhttp.get().url(url).tag(this).enqueue(new GsonResponseHandler<FamilyMedicalDetailInfo>() {
            @Override
            public void onSuccess(int statusCode, FamilyMedicalDetailInfo response) {
                if (response.getCode() != 0) {
                    toast(response.getMessage());
                    return;
                }
                mFamilyMedicalDetailInfo = response.getData();
                showFamilyMedicalDetailInfo(mFamilyMedicalDetailInfo);
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {

            }
        });
    }

    private void showFamilyMedicalDetailInfo(FamilyMedicalDetailInfo.DataBean detailInfo) {
        if (detailInfo == null) {
            return;
        }
        tvDoctorInfo.setText(new StringBuffer(detailInfo.getTeamCaptain() == null ? "" : detailInfo.getTeamCaptain())
                .append("  ")
                .append(detailInfo.getPosition() == null ? "" : detailInfo.getPosition()).toString());
        tvContractTeamName.setText(detailInfo.getTeamName() == null ? "" : detailInfo.getTeamName());
        tvTeamType.setText(detailInfo.getTeamDesc() == null ? "" : detailInfo.getTeamDesc());
        GlidUtil.loadImage(this, detailInfo.getPicturePath(), ivDoctorHeader);
    }

    private void obtainPaintList() {
        String url = HttpUrl.QueryPatientVisitByAccountId + "?AccountId=" + 30;
        mMyOkhttp.get().url(url).tag(this).enqueue(new GsonResponseHandler<PaintListEntity>() {
            @Override
            public void onSuccess(int statusCode, PaintListEntity response) {
                if (response.getCode() != 0) {
                    toast(response.getMessage());
                    return;
                }
                mPaintList = response.getData();
                showPaintInfo(mPaintList);
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {

            }
        });
    }

    private void showPaintInfo(List<PaintListEntity.DataBean> mPaintList) {
        if (mPaintList == null) {
            showEmptyPaintInfo();
            return;
        }

        if (mPaintList.size() == 0) {
            showEmptyPaintInfo();
            return;
        }

        showFirstPaintInfo();
    }

    private void showFirstPaintInfo() {
        PaintListEntity.DataBean info = mPaintList.get(0);
        tvContractName.setText(info.getPatientName() == null ? getString(R.string.notKnow) : info.getPatientName());
        tvContactIdCard.setText(info.getCredNo() == null ? getString(R.string.notKnow) : info.getCredNo());
        tvContractTel.setText(info.getMobile() == null ? getString(R.string.notKnow) : info.getMobile());
        GlidUtil.loadImage(this, info.getPicturePath(), ivUserHeader);
    }


    private void showEmptyPaintInfo() {
        tvContractName.setText(SpUtils.getString(Contants.userName));
        tvContactIdCard.setText(getString(R.string.notKnow));
        tvContractTel.setText(SpUtils.getString(Contants.Mobile));
    }

    @Override
    protected void otherViewClick(View view) {

    }

}
