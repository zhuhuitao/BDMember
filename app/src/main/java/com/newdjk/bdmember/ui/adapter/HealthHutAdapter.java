package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.HealthHutEntity;
import com.newdjk.bdmember.utils.GlidUtil;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;

import java.util.List;

public class HealthHutAdapter extends BaseQuickAdapter<HealthHutEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private Context mContext;
    private RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public HealthHutAdapter(@Nullable List<HealthHutEntity.DataBean.ReturnDataBean> data, Context mContext, RecyclerViewItemClickListener listener) {
        super(R.layout.adapter_health_hub_activies, data);
        this.mContext = mContext;
        this.mRecyclerViewItemClickListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthHutEntity.DataBean.ReturnDataBean item) {
        ImageView ivActivityThem = helper.getView(R.id.iv_hub_activities_them);
        if (item != null) {
            GlidUtil.loadImage(mContext, item.getHouseImgPath(), ivActivityThem);
            helper.setText(R.id.tv_hub_them, item.getHealthHouseName() == null ? "" : item.getHealthHouseName());
            helper.setText(R.id.tv_hub_time, item.getSeriveTime() == null ? "" : item.getSeriveTime());
            helper.setText(R.id.tv_hub_address, item.getHouseAddress() == null ? "" : item.getHouseAddress());
            if (item.getIsActivity() == 0) {
                helper.setText(R.id.tv_hub_status, R.string.activities_over);
            } else if (item.getIsActivity() == 1) {
                helper.setText(R.id.tv_hub_status, R.string.activities_playing);
            }
            helper.getView(R.id.rl_hub).setOnClickListener(v -> mRecyclerViewItemClickListener.recyclerViewClickListener(0, item));
        }
    }
}
