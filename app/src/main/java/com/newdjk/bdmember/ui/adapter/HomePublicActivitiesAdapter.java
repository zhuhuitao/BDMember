package com.newdjk.bdmember.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.PublicActivitiesEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePublicActivitiesAdapter extends RecyclerView.Adapter<HomePublicActivitiesAdapter.MyViewHolder> {


    private List<PublicActivitiesEntity> mList;

    public HomePublicActivitiesAdapter(List<PublicActivitiesEntity> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_home_public_activities, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (myViewHolder != null) {
            if (i == mList.size() - 1) {
                myViewHolder.itemLine.setVisibility(View.GONE);

            } else {
                myViewHolder.itemLine.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_activity_them)
        ImageView ivHomeActivityThem;
        @BindView(R.id.iv_home_activity_type)
        ImageView ivHomeActivityType;
        @BindView(R.id.item_line)
        View itemLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
