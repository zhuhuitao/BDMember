package com.newdjk.bdmember.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.AreaEntity;
import com.newdjk.bdmember.ui.adapter.HealthAreaAdapter;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.PopItemClickListener;
import com.newdjk.bdmember.utils.RecyclerViewItemClickListener;

import java.util.List;


public class HealthHubWindow extends PopupWindow implements RecyclerViewItemClickListener {
    View mView;
    private RecyclerView mRv;
    private HealthAreaAdapter mAdapter;
    private LinearLayoutManager mManager;
    private PopItemClickListener mListener;

    public void setItemClickListener(PopItemClickListener listener) {
        this.mListener = listener;
    }

    public HealthHubWindow(Context context, List<AreaEntity.DataBean> list) {
        if (mView == null) {
            mView = LayoutInflater.from(context).inflate(R.layout.pop_health_window, null);
        }
        mRv = mView.findViewById(R.id.rv);
        if (list == null) {
            return;
        }
        if (mManager == null) {
            mManager = new LinearLayoutManager(context);
            mManager.setOrientation(LinearLayoutManager.VERTICAL);
        }
        if (mView != null) {
            mRv.setLayoutManager(mManager);
        }
        if (mAdapter == null) {
            mAdapter = new HealthAreaAdapter(list, context, this);
        }
        mRv.setAdapter(mAdapter);
        this.setContentView(mView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.tm50));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

    }

    @Override
    public void recyclerViewClickListener(int position, Object o) {
        if (mAdapter != null) {
            Contants.POSITION = position;
        }
        mListener.popItemClickListener(position, o);
    }
}
