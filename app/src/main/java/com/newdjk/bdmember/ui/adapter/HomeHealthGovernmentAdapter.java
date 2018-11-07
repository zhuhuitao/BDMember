package com.newdjk.bdmember.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.HealthGovernmentEntity;
import com.newdjk.bdmember.utils.HomeItemClickListener;
import com.newdjk.bdmember.widget.CircleImageView;

import java.util.List;

public class HomeHealthGovernmentAdapter extends BaseQuickAdapter<HealthGovernmentEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private HomeItemClickListener mListener;

    public HomeHealthGovernmentAdapter(@Nullable List<HealthGovernmentEntity.DataBean.ReturnDataBean> data, HomeItemClickListener mListener) {
        super(R.layout.adapter_home_health_government, data);
        this.mListener = mListener;
    }


    @Override
    protected void convert(BaseViewHolder helper, HealthGovernmentEntity.DataBean.ReturnDataBean item) {
        if (item != null) {
            ImageView ivThem = helper.getView(R.id.iv_home_health_government_them);
            Glide.with(mContext)
                    .load(item.getMasterMap())
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .error(R.mipmap.ic_group2)
                    .placeholder(R.mipmap.ic_group2)
                    .into(ivThem);

            helper.setText(R.id.tv_home_health_government_them, item.getTitle() == null ? mContext.getString(R.string.titleError) : item.getTitle());
            helper.setText(R.id.tv_home_health_government_time, item.getCreateTime() == null ? mContext.getString(R.string.timeErrror) : item.getCreateTime().substring(0, item.getCreateTime().length() - 3));
            helper.getView(R.id.item_healthGovernment).setOnClickListener(v -> mListener.homeItemListener(HomeItemClickListener.healGovernment, item));
        }


    }
}
