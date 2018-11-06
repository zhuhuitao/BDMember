package com.newdjk.bdmember.ui.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.HealthGovernmentEntity;
import com.newdjk.bdmember.widget.CircleImageView;

import java.util.List;

public class HomeHealthGovernmentAdapter extends BaseQuickAdapter<HealthGovernmentEntity, BaseViewHolder> {


    public HomeHealthGovernmentAdapter(@Nullable List<HealthGovernmentEntity> data) {
        super(R.layout.adapter_home_health_government, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, HealthGovernmentEntity item) {

    }
}
