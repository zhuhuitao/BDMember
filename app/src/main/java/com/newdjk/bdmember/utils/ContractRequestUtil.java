package com.newdjk.bdmember.utils;

import com.lxq.okhttp.response.GsonResponseHandler;
import com.newdjk.bdmember.MyApplication;
import com.newdjk.bdmember.bean.ServicePackageEntity;

import java.util.HashMap;

public class ContractRequestUtil {
    public static ContractRequestUtil getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static ContractRequestUtil instance = new ContractRequestUtil();
    }


    public void doHttpRequest(int pages, int index, String type, BaseCallback callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("ServicePackName", "");
        params.put("DrId", "-1");
        params.put("OrgId", "-1");
        params.put("HosGroupId", "-1");
        params.put("ServiceType", type);
        params.put("PageIndex", pages + "");
        params.put("PageSize", index + "");
        MyApplication.getInstance().getmMyOkHttp().post().url(HttpUrl.QueryServicePackPageAndDetail).params(params).tag(this).enqueue(new GsonResponseHandler<ServicePackageEntity>() {
            @Override
            public void onSuccess(int statusCode, ServicePackageEntity response) {

                callback.success(response);
            }

            @Override
            public void onFailures(int statusCode, String errorMsg) {
                callback.failed(statusCode, errorMsg);
            }
        });

    }
}
