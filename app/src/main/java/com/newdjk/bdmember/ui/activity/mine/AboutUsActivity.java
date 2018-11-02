package com.newdjk.bdmember.ui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.MyApplication;
import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.bean.AboutUsEntity;
import com.newdjk.bdmember.ui.activity.login.AgreementActivity;
import com.newdjk.bdmember.utils.Contants;
import com.newdjk.bdmember.utils.HttpUrl;
import com.newdjk.bdmember.utils.SpUtils;
import com.newdjk.bdmember.utils.SystemUitl;
import com.newdjk.bdmember.widget.LoadDialog;

import butterknife.BindView;

public class AboutUsActivity extends BasicActivity {


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
    @BindView(R.id.mVersion)
    TextView mVersion;
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;
    @BindView(R.id.tv_mail)
    TextView tvMail;
    @BindView(R.id.tv_tel)
    TextView tvTel;


    private String[] mAppInfo;
    private final static String TAG = "aboutUs";

    @Override
    protected int initViewResId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        initTitle("关于我们").setLeftImage(R.mipmap.head_back_n).setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleBgRes(R.color.white);
        getLocalVersion(MyApplication.getContext());

    }


    public void getLocalVersion(Context context) {
        String localVersion;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            mVersion.setText("当前版本号：V" + localVersion);
            Log.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        tv_agreement.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mAppInfo = SystemUitl.packageCode(this);
        loadInfo();
        obtainAboutInfo();
    }

    private void obtainAboutInfo() {
        loading(true);
        Log.d(TAG, "obtainAboutInfo: " + HttpUrl.GetAboutInfo);
        mMyOkhttp.post().url(HttpUrl.GetAboutInfo).tag(this).enqueue(new GsonResponseHandler<AboutUsEntity>() {
            @Override
            public void onSuccess(int statusCode, AboutUsEntity response) {
                LoadDialog.clear();
                if (response.getCode() == 0) {
                    tvTel.setText(new StringBuffer("客服热线:").append(response.getData().getMobile()));
                    tvMail.setText(new StringBuffer("客服邮箱:").append(response.getData().getMail()));
                }
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                LoadDialog.clear();
            }
        });
    }

    private void loadInfo() {
        if (mAppInfo != null) {
            if (mAppInfo.length != 0) {
                mVersion.setText("当前版本:v" + mAppInfo[1]);
            }
        }

    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_agreement:
                Intent intent = new Intent(AboutUsActivity.this, AgreementActivity.class);
                intent.putExtra("userInfo", SpUtils.getString(Contants.LoginJson));
                startActivity(intent);
                // toActivity(AgreementActivity.getInten(mContext));
                break;
        }
    }


}
