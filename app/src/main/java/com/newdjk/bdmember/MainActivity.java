package com.newdjk.bdmember;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;


import com.newdjk.bdmember.ui.fragment.ContractFragment;
import com.newdjk.bdmember.ui.fragment.HealthFragment;
import com.newdjk.bdmember.ui.fragment.HomeFragment;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.ui.fragment.MineFragment;
import com.newdjk.bdmember.ui.fragment.OnlineFragment;
import com.newdjk.bdmember.widget.BottomTabRadioButton;
import com.newdjk.bdmember.widget.FragmentController;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends BasicActivity {
    @BindView(R.id.rb_tab1)
    BottomTabRadioButton rbTab1;
    @BindView(R.id.rb_tab2)
    BottomTabRadioButton rbTab2;
    @BindView(R.id.rb_tab3)
    BottomTabRadioButton rbTab3;
    @BindView(R.id.rb_tab4)
    BottomTabRadioButton rbTab4;
    @BindView(R.id.rb_tab5)
    BottomTabRadioButton rbTab5;
    private List<Fragment> listFragment = new ArrayList();
    private FragmentController fgtController = null;

    public static Intent getAct(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int initViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
//            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
//        }
    }

    @Override
    protected void changeStatusBarTextColor(boolean isBlack) {
        super.changeStatusBarTextColor(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        rbTab1.setDrawablesTop(R.drawable.home_selectot);
        rbTab2.setDrawablesTop(R.drawable.monitor_selectot);
        rbTab3.setDrawablesTop(R.drawable.message_selectot);
        rbTab4.setDrawablesTop(R.drawable.personal_selectot);
        listFragment.add(HomeFragment.getFragment());
        listFragment.add(OnlineFragment.getFragment());
        listFragment.add(ContractFragment.getFragment());
        listFragment.add(HealthFragment.getFragment());
        listFragment.add(MineFragment.getFragment());
        fgtController = new FragmentController(this, R.id.fl_main, listFragment);
    }

    @Override
    protected void initListener() {
        rbTab1.setOnClickListener(this);
        rbTab2.setOnClickListener(this);
        rbTab3.setOnClickListener(this);
        rbTab4.setOnClickListener(this);
        rbTab5.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        fgtController.showFragment(0);
        rbTab1.setChecked(true);
        rbTab2.setChecked(false);
        rbTab3.setChecked(false);
        rbTab4.setChecked(false);
        rbTab5.setChecked(false);
    }

    @Override
    protected void otherViewClick(View view) {
        switch (view.getId()) {
            case R.id.rb_tab1:
                fgtController.showFragment(0);
                rbTab1.setChecked(true);
                rbTab2.setChecked(false);
                rbTab3.setChecked(false);
                rbTab4.setChecked(false);
                rbTab5.setChecked(false);
                break;
            case R.id.rb_tab2:
                fgtController.showFragment(1);
                rbTab1.setChecked(false);
                rbTab2.setChecked(true);
                rbTab3.setChecked(false);
                rbTab4.setChecked(false);
                rbTab5.setChecked(false);
                break;
            case R.id.rb_tab3:
                fgtController.showFragment(2);
                rbTab1.setChecked(false);
                rbTab2.setChecked(false);
                rbTab3.setChecked(true);
                rbTab4.setChecked(false);
                rbTab5.setChecked(false);
                break;
            case R.id.rb_tab4:
                fgtController.showFragment(3);
                rbTab1.setChecked(false);
                rbTab2.setChecked(false);
                rbTab3.setChecked(false);
                rbTab4.setChecked(true);
                rbTab5.setChecked(false);
                break;
            case R.id.rb_tab5:
                fgtController.showFragment(4);
                rbTab1.setChecked(false);
                rbTab2.setChecked(false);
                rbTab3.setChecked(false);
                rbTab4.setChecked(false);
                rbTab5.setChecked(true);
                break;
        }
    }


    public void toHomeFragment() {
        fgtController.showFragment(0);
        rbTab1.setChecked(true);
        rbTab2.setChecked(false);
        rbTab3.setChecked(false);
        rbTab4.setChecked(false);
    }


    private long exitTime = 0;

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Fragment fragment = fgtController.getFragment();
//            if (fragment instanceof FunctionFragment) {
//                FunctionFragment mFunctionFragment = (FunctionFragment) fragment;
//                mFunctionFragment.canGoBackWebView();
//                return;
//            }
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            killAll();
        }
    }


    public void exitNoTip() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            killAll();
            System.exit(0);
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
