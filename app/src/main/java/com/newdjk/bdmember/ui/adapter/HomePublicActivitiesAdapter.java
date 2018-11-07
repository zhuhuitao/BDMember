package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.PublicActivitiesEntity;
import com.newdjk.bdmember.utils.HomeItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePublicActivitiesAdapter extends RecyclerView.Adapter<HomePublicActivitiesAdapter.MyViewHolder> {

    private Context mContext;

    private List<PublicActivitiesEntity.DataBean.ReturnDataBean> mList;

    private HomeItemClickListener mListener;

    public HomePublicActivitiesAdapter(List<PublicActivitiesEntity.DataBean.ReturnDataBean> mList, Context mContext, HomeItemClickListener mListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.mListener = mListener;
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
            Glide.with(mContext)
                    .load(mList.get(i).getContent())
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .error(R.mipmap.ic_group2)
                    .placeholder(R.mipmap.ic_group2)
                    .into(myViewHolder.ivHomeActivityThem);
            myViewHolder.tvActivitiesThem.setText(mList.get(i).getTitle() == null ? "" : mList.get(i).getTitle());
            myViewHolder.tvActivitiesTime.setText(mList.get(i).getStartTime() == null ? "" : mList.get(i).getStartTime().substring(0, mList.get(i).getStartTime().length() - 3));
            SpannableString spannableString = new SpannableString(new StringBuffer(mList.get(i).getEnrollment() + "").append(mContext.getString(R.string.singUp_ed)));
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFA416")), 0, spannableString.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            myViewHolder.tvActivitiesSingUpNum.setText(spannableString);
            if (mList.get(i).getStatus() == 1) {
                //活动进行中
            } else if (mList.get(i).getStatus() == 2) {
                //活动已过期
            }
            if (i == mList.size() - 1) {
                myViewHolder.itemLine.setVisibility(View.GONE);

            } else {
                myViewHolder.itemLine.setVisibility(View.VISIBLE);
            }

            myViewHolder.rlIv.setOnClickListener(v -> mListener.homeItemListener(HomeItemClickListener.homePublicActivities, mList.get(i)));
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
        @BindView(R.id.rl_iv)
        RelativeLayout rlIv;
        @BindView(R.id.item_line)
        View itemLine;
        @BindView(R.id.tv_activities_them)
        TextView tvActivitiesThem;
        @BindView(R.id.tv_activities_time)
        TextView tvActivitiesTime;
        @BindView(R.id.tv_activities_sing_up_num)
        TextView tvActivitiesSingUpNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
