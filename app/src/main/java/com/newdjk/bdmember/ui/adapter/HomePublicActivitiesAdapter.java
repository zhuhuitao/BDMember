package com.newdjk.bdmember.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamousDoctorOrNurseEntity;
import com.newdjk.bdmember.bean.PublicActivitiesEntity;
import com.newdjk.bdmember.widget.CircleImageView;

import java.util.List;

public class HomePublicActivitiesAdapter extends BaseQuickAdapter<PublicActivitiesEntity, BaseViewHolder> {


    public HomePublicActivitiesAdapter(@Nullable List<PublicActivitiesEntity> data) {
        super(R.layout.adapter_home_public_activities, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicActivitiesEntity item) {

    }


}
