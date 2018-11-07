package com.newdjk.bdmember.ui.adapter;


import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.utils.HomeItemClickListener;
import com.newdjk.bdmember.widget.CircleImageView;

import java.util.List;

public class ContractServicePackageAdapter extends BaseQuickAdapter<ServicePackageEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private int mType;
    private HomeItemClickListener mListener;

    public ContractServicePackageAdapter(@Nullable List<ServicePackageEntity.DataBean.ReturnDataBean> data, HomeItemClickListener listener) {
        super(R.layout.adapter_contract_base_service_package, data);
        this.mListener = listener;
    }

    public void setType(int type) {
        this.mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicePackageEntity.DataBean.ReturnDataBean item) {
        if (item != null) {
            ImageView ivThem = helper.getView(R.id.iv_service_package_them);
            Glide.with(mContext)
                    .load(item.getMasterPicture())
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .error(R.mipmap.ic_group2)
                    .placeholder(R.mipmap.ic_group2)
                    .into(ivThem);
            helper.setText(R.id.tv_package_name, item.getServicePackName() == null ? mContext.getString(R.string.baseServicePackageName) : item.getServicePackName());
            SpannableString spannableString = new SpannableString(new StringBuffer().append(item.getBuyNum()).append(mContext.getString(R.string.alreadyBuy)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA416")), 0, spannableString.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_buyer_num, spannableString);
            helper.setText(R.id.tv_price, new StringBuffer("￥").append(item.getPrice()));
        }

    }
}
