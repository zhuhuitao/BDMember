package com.newdjk.bdmember.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.PregnantMotherServicePackageEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePregnantMotherServicePackageAdapter extends RecyclerView.Adapter<HomePregnantMotherServicePackageAdapter.MyViewHolder> {
    private List<PregnantMotherServicePackageEntity> mList;

    public HomePregnantMotherServicePackageAdapter(List<PregnantMotherServicePackageEntity> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home_pregnant_mother_service_package_recylerview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_pregnant_mother_service_package_picture_them)
        ImageView ivHomePregnantMotherServicePackagePictureThem;
        @BindView(R.id.tv_home_pregnant_mother_service_package_name)
        TextView tvHomePregnantMotherServicePackageName;
        @BindView(R.id.tv_home_pregnant_mother_service_price)
        TextView tvHomePregnantMotherServicePrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
