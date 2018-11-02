package com.newdjk.bdmember.widget;


import com.newdjk.bdmember.utils.LogUtils;
import com.newdjk.bdmember.utils.ToastUtil;

public class CommonMethod<T> {

    /**
     * 请求失败统一处理
     *
     * @param statusCode
     * @param errorMsg
     */
    public static void requestError(int statusCode, String errorMsg) {
        LogUtils.e("statusCode:" + statusCode + "    errorMsg:" + errorMsg);
        ToastUtil.setToast(errorMsg);
        if (statusCode == 401) {
//            errorLoginActivty();
        }
        LoadDialog.clear();
    }
}
