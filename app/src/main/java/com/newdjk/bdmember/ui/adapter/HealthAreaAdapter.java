package com.newdjk.bdmember.ui.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.AreaEntity;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HealthAreaAdapter extends RecyclerView.Adapter<HealthAreaAdapter.MyViewHolder> {

    private List<AreaEntity.DataBean> mList;
    private Context mContext;

    private RecyclerViewItemClickListener mListener;


    public HealthAreaAdapter(List<AreaEntity.DataBean> mList, Context mContext, RecyclerViewItemClickListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_health_area, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (i == Contants.POSITION) {
            myViewHolder.tvArea.setBackgroundResource(R.drawable.pop_white_bg   );
            myViewHolder.tvArea.setTextColor(mContext.getResources().getColor(R.color.theme));
        } else {
            myViewHolder.tvArea.setBackgroundResource(R.drawable.pop_gray_bg);
            myViewHolder.tvArea.setTextColor(mContext.getResources().getColor(R.color.shallow_gray_text));
        }

        myViewHolder.tvArea.setText(mList.get(i).getAreaName());

        myViewHolder.tvArea.setOnClickListener(v -> mListener.recyclerViewClickListener(i, mList.get(i)));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_area)
        TextView tvArea;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
