package com.newdjk.bdmember.bean;

import java.util.List;

public class PaintListEntity {


    /**
     * Code : 0
     * Message :
     * Data : [{"PatientId":48,"AccountId":30,"PatientName":"张黔","NameLetter":"ZQ","PatientSex":2,"Birthday":"1993-05-04 00:00:00","CredType":2,"CredNo":null,"Mobile":"18688955900","PicturePath":"http://userimage.newstarthealth.cn/pat/0/48.jpg?dt=201811102948145","PatientType":0,"Newborn":1,"DefaultPatient":1,"CreateTime":"2018-09-26 15:27:03","CredTypeName":"港澳台回乡证","Age":"25岁"}]
     */

    private int Code;
    private String Message;
    private List<DataBean> Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * PatientId : 48
         * AccountId : 30
         * PatientName : 张黔
         * NameLetter : ZQ
         * PatientSex : 2
         * Birthday : 1993-05-04 00:00:00
         * CredType : 2
         * CredNo : null
         * Mobile : 18688955900
         * PicturePath : http://userimage.newstarthealth.cn/pat/0/48.jpg?dt=201811102948145
         * PatientType : 0
         * Newborn : 1
         * DefaultPatient : 1
         * CreateTime : 2018-09-26 15:27:03
         * CredTypeName : 港澳台回乡证
         * Age : 25岁
         */

        private int PatientId;
        private int AccountId;
        private String PatientName;
        private String NameLetter;
        private int PatientSex;
        private String Birthday;
        private int CredType;
        private String CredNo;
        private String Mobile;
        private String PicturePath;
        private int PatientType;
        private int Newborn;
        private int DefaultPatient;
        private String CreateTime;
        private String CredTypeName;
        private String Age;

        public int getPatientId() {
            return PatientId;
        }

        public void setPatientId(int PatientId) {
            this.PatientId = PatientId;
        }

        public int getAccountId() {
            return AccountId;
        }

        public void setAccountId(int AccountId) {
            this.AccountId = AccountId;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String PatientName) {
            this.PatientName = PatientName;
        }

        public String getNameLetter() {
            return NameLetter;
        }

        public void setNameLetter(String NameLetter) {
            this.NameLetter = NameLetter;
        }

        public int getPatientSex() {
            return PatientSex;
        }

        public void setPatientSex(int PatientSex) {
            this.PatientSex = PatientSex;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public int getCredType() {
            return CredType;
        }

        public void setCredType(int CredType) {
            this.CredType = CredType;
        }

        public String getCredNo() {
            return CredNo;
        }

        public void setCredNo(String CredNo) {
            this.CredNo = CredNo;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getPicturePath() {
            return PicturePath;
        }

        public void setPicturePath(String PicturePath) {
            this.PicturePath = PicturePath;
        }

        public int getPatientType() {
            return PatientType;
        }

        public void setPatientType(int PatientType) {
            this.PatientType = PatientType;
        }

        public int getNewborn() {
            return Newborn;
        }

        public void setNewborn(int Newborn) {
            this.Newborn = Newborn;
        }

        public int getDefaultPatient() {
            return DefaultPatient;
        }

        public void setDefaultPatient(int DefaultPatient) {
            this.DefaultPatient = DefaultPatient;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCredTypeName() {
            return CredTypeName;
        }

        public void setCredTypeName(String CredTypeName) {
            this.CredTypeName = CredTypeName;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }
    }
}
