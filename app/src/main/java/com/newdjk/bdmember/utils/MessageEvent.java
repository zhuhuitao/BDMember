package com.newdjk.bdmember.utils;


import com.newdjk.bdmember.bean.WXUserInfo;

/**
 * Created by EDZ on 2018/9/20.
 */

public class MessageEvent {

    private int mSericeItemCode;
    private String mType;
    private int payCode;
    private WXUserInfo userInfo;
    public int getPayCode() {
        return payCode;
    }

    public void setPayCode(int payCode) {
        this.payCode = payCode;
    }

    public WXUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WXUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private int DrType;

    public int getDrType() {
        return DrType;
    }

    public void setDrType(int drType) {
        DrType = drType;
    }

    public MessageEvent() {
    }

    public int getmSericeItemCode() {
        return mSericeItemCode;
    }

    public void setmSericeItemCode(int mSericeItemCode) {
        this.mSericeItemCode = mSericeItemCode;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }
}
