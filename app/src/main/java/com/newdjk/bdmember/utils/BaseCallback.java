package com.newdjk.bdmember.utils;

public interface BaseCallback {
    /**
     * 请求成功
     *
     * @param o
     */
    void success(Object o);

    /**
     * 请求成功
     *
     * @param errorCode
     * @param errorMsg
     */
    void failed(int errorCode, String errorMsg);
}
