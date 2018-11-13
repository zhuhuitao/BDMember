package com.newdjk.bdmember.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.newdjk.bdmember.R;
import com.newdjk.bdmember.basic.BasicActivity;
import com.newdjk.bdmember.scan.activity.CaptureFragment;
import com.newdjk.bdmember.scan.activity.CodeUtils;


public class ScanActivity extends BasicActivity {

    private final int REQUEST_IMAGE = 001;

    @Override
    protected int initViewResId() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initView() {
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);

        captureFragment.setAnalyzeCallback(analyzeCallback);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }

    @Override
    protected void initListener() {
//        btnOpenAlbum.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(intent, REQUEST_IMAGE);
//        });


    }

    @Override
    protected void initData() {
        TextView tv = getSupportFragmentManager().findFragmentById(R.id.ff).getView().findViewById(R.id.tv_des);
        tv.setText("测试东西");
    }

    @Override
    protected void otherViewClick(View view) {

    }



    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ScanActivity.this.setResult(RESULT_OK, resultIntent);
            ScanActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanActivity.this.setResult(RESULT_OK, resultIntent);
            ScanActivity.this.finish();
        }
    };

}
