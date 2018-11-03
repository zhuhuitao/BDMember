package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.HomeItemBean;
import com.newdjk.bdmember.utils.ItemOnClickCall;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecyclerViewFirstAdapter extends RecyclerView.Adapter<HomeRecyclerViewFirstAdapter.MyViewHolder> {

    private Context mContext;
    private HomeItemBean mItemBean;
    private ItemOnClickCall mItemClick;

    public HomeRecyclerViewFirstAdapter(Context context, ItemOnClickCall itemOnClickCall) {
        this.mContext = context;
        this.mItemBean = new HomeItemBean();
        this.mItemClick = itemOnClickCall;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_home_recylerview, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (mItemBean.mListIc.size() > 0 && mItemBean.mListText.size() > 0) {
            Drawable drawable = mContext.getResources().getDrawable(mItemBean.mListIc.get(i));
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            myViewHolder.item.setCompoundDrawables(null, drawable, null, null);
            myViewHolder.item.setText(mItemBean.mListText.get(i));
            myViewHolder.item.setOnClickListener(v -> mItemClick.itemClickListener());
        }
    }

    @Override
    public int getItemCount() {
        return mItemBean.mListIc == null ? 0 : mItemBean.mListIc.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item)
        TextView item;
        @BindView(R.id.line)
        View line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
