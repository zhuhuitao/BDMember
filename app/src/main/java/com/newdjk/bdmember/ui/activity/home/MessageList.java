package com.newdjk.bdmember.ui.activity.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

public class MessageList extends BasicActivity {
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.top_title)
    TextView topTitle;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.relat_titlebar)
    RelativeLayout relatTitlebar;
    @BindView(R.id.liear_titlebar)
    LinearLayout liearTitlebar;
    @BindView(R.id.rv_health)
    RecyclerView rvHealth;
    @BindView(R.id.health_refreshLayout)
    SmartRefreshLayout healthRefreshLayout;

    @Override
    protected int initViewResId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void otherViewClick(View view) {

    }

}
