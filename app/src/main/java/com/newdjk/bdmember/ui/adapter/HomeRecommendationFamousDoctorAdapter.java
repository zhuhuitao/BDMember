package com.newdjk.bdmember.ui.adapter;


import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.utils.HomeItemClickListener;
import com.newdjk.bdmember.widget.CircleImageView;


import java.util.List;

public class HomeRecommendationFamousDoctorAdapter extends BaseQuickAdapter<FamousDoctorOrNurseEntity.DataBean, BaseViewHolder> {
    private int mType;
    private HomeItemClickListener mListener;

    public HomeRecommendationFamousDoctorAdapter(@Nullable List<FamousDoctorOrNurseEntity.DataBean> data, HomeItemClickListener listener) {
        super(R.layout.adapter_home_famous_doctor_, data);
        this.mListener = listener;
    }

    public void setmType(int type) {
        this.mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, FamousDoctorOrNurseEntity.DataBean item) {
        CircleImageView view = helper.getView(R.id.mPhoto);
        if (mType == 1) {
            Glide.with(mContext).load(item.getPicturePath()).error(R.mipmap.ic_home_famous_doctor_header).into(view);
        } else {
            Glide.with(mContext).load(item.getPicturePath()).error(R.mipmap.ic_home_nurse_header).into(view);
        }
        helper.setText(R.id.mName, item.getDrName());
        helper.setText(R.id.mHospitalName, item.getHospitalName());
        helper.setText(R.id.mDepartmentName, item.getDepartmentName());
        helper.setBackgroundRes(R.id.mHospitalName, mType == 1 ? R.color.famous_doctor_bg : R.color.famous_nurse_bg);
        helper.setText(R.id.mPraiseRate, item.getPraiseRate() + "%");
        helper.setText(R.id.mConsultCount, item.getConsultCount() + "");
        helper.getView(R.id.mContainer).setOnClickListener(v -> mListener.homeItemListener(HomeItemClickListener.homeFamousDoctor, item));
    }
}
