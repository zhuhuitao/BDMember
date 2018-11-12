package com.newdjk.bdmember.utils;

import android.os.AsyncTask;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.MyApplication;
import com.newdjk.bdmember.bean.PicturePathEntity;
import com.newdjk.bdmember.bean.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public enum UploadPictureUitl {
    INSTANCE;

    private BaseCallback mCallBack;

    public void setCallback(BaseCallback baseCallback) {
        this.mCallBack = baseCallback;
    }

    public void uploadPicture(String path) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String image64 = ImageBase64.bitmapToString(strings[0]);
                return image64;
            }

            @Override
            protected void onPostExecute(String s) {
                Map<String, String> map = new HashMap<>();
                map.put("Base64Str", s);
                MyApplication.getInstance().getmMyOkHttp().post().url(HttpUrl.updatePhoto).params(map).tag(this).enqueue(new GsonResponseHandler<ResponseEntity<PicturePathEntity>>() {
                    @Override
                    public void onSuccess(int statusCode, ResponseEntity<PicturePathEntity> entituy) {
                        mCallBack.success(entituy);
                    }

                    @Override
                    public void onFailures(int statusCode, String errorMsg) {
                        mCallBack.failed(statusCode, errorMsg);
                    }
                });
            }
        }.execute(path);

    }
}
