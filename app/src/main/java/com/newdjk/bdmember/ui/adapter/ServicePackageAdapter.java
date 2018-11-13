package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.utils.GlidUtil;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;

import java.util.List;

public class ServicePackageAdapter extends BaseQuickAdapter<ServicePackageEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private Context mContext;
    private RecyclerViewItemClickListener mListener;


    public ServicePackageAdapter(@Nullable List<ServicePackageEntity.DataBean.ReturnDataBean> data, Context mContext, RecyclerViewItemClickListener mListener) {
        super(R.layout.adapter_service_package, data);
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicePackageEntity.DataBean.ReturnDataBean item) {
        if (item != null) {
            ImageView iv = helper.getView(R.id.iv_service_package);
            GlidUtil.loadImage(mContext, null, iv);
            helper.setText(R.id.tv_package_name, item.getServicePackName() == null ? "" : item.getServicePackName());
            helper.setText(R.id.tv_price, new StringBuffer("￥").append(item.getPrice()));
            TextView tv = helper.getView(R.id.tv_past_price);
            tv.setText(new StringBuffer("￥").append(item.getOriginalPrice()));
            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            helper.setText(R.id.tv_has_buy, new StringBuffer().append(item.getBuyNum()).append(mContext.getResources().getString(R.string.alreadyBuy)));
            helper.setText(R.id.tv_service_people, new StringBuffer().append(mContext.getResources().getString(R.string.servicePeople)).append(item.getFitPeople() == null ? "" : item.getFitPeople()));
            helper.getView(R.id.rl_service_package).setOnClickListener(v -> mListener.recyclerViewClickListener(0, item));
        }

    }
}
