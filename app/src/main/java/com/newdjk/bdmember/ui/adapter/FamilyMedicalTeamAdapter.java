package com.newdjk.bdmember.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.bean.FamilyMedicalTeamEntity;
import com.newdjk.bdmember.utils.RecyclerViewItemGroupClickListener;

import java.util.List;

public class FamilyMedicalTeamAdapter extends BaseQuickAdapter<FamilyMedicalTeamEntity.DataBean.ReturnDataBean, BaseViewHolder> {
    private Context mContext;
    private RecyclerViewItemGroupClickListener mListener;

    public FamilyMedicalTeamAdapter(Context context, @Nullable List<FamilyMedicalTeamEntity.DataBean.ReturnDataBean> data, RecyclerViewItemGroupClickListener mListener) {
        super(R.layout.adapter_family_medical_team, data);
        this.mContext = context;
        this.mListener = mListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyMedicalTeamEntity.DataBean.ReturnDataBean item) {
        if (item != null) {
            helper.setText(R.id.tv_family_them, item.getTeamName() == null ? "" : item.getTeamName());
            helper.setText(R.id.tv_family_team_name, item.getTeamCaptain() == null ? "" : new StringBuffer(mContext.getString(R.string.teamLeader)).append(item.getTeamCaptain()));
            helper.getView(R.id.tv_see_detail).setOnClickListener(v -> mListener.recyclerViewChildrenClickListener(item));
            helper.getView(R.id.rl_item).setOnClickListener(v -> mListener.recyclerViewItemClickListener(item));
        }
    }
}
