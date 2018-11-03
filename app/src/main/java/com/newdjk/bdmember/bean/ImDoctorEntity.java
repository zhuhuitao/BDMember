package com.newdjk.bdmember.bean;

import java.io.Serializable;

/**
 * Created by EDZ on 2018/11/1.
 */

public class ImDoctorEntity implements Serializable {


    /**
     * DrName : 钟大潘
     * DrId : 18
     * FaceUrl : http://userimage.newstarthealth.cn/doc/0/18.jpg?dt=201811012414186
     * Identifier : doc_18
     */

    private String DrName;
    private int DrId;
    private String FaceUrl;
    private String Identifier;
    private int serviceCode;

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getDrName() {
        return DrName;
    }

    public void setDrName(String DrName) {
        this.DrName = DrName;
    }

    public int getDrId() {
        return DrId;
    }

    public void setDrId(int DrId) {
        this.DrId = DrId;
    }

    public String getFaceUrl() {
        return FaceUrl;
    }

    public void setFaceUrl(String FaceUrl) {
        this.FaceUrl = FaceUrl;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String Identifier) {
        this.Identifier = Identifier;
    }
}
