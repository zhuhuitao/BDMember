package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.ServicePackageEntity;
import com.newdjk.bdmember.utils.GlidUtil;

import java.util.List;

public class ServicePackageAdapter extends BaseQuickAdapter<ServicePackageEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private Context mContext;

    public ServicePackageAdapter(@Nullable List<ServicePackageEntity.DataBean.ReturnDataBean> data, Context mContext) {
        super(R.layout.adapter_service_package, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ServicePackageEntity.DataBean.ReturnDataBean item) {
        ImageView iv = helper.getView(R.id.iv_service_package);
        GlidUtil.loadImage(mContext,null,iv);
    }
}
