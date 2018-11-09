package com.newdjk.bdmember.ui.activity.contract;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.widget.CircleImageView;

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

    @Override
    protected int initViewResId() {
        return R.layout.activity_fill_contact_information;
    }

    @Override
    protected void initView() {

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

}
