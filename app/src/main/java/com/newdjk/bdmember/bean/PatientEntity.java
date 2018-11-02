package com.newdjk.bdmember.bean;

/**
 * Created by EDZ on 2018/10/25.
 */

public class PatientEntity {


    /**
     * Code : 0
     * Message :
     * Data : {"PatientId":60,"AccountId":35,"PatientName":"","NameLetter":"","PatientSex":3,"Birthday":null,"CredType":null,"CredNo":null,"Mobile":"13823230159","Phone":null,"PicturePath":"http://userimage.newstarthealth.cn/pat/0/60.jpg?dt=201810251345580","MaritalStatus":null,"AreaId":null,"AreaName":null,"Region":null,"PatientType":0,"Job":-1,"Education":-1,"MedicalType":-1,"SocialNum":null,"UrgentPhone":null,"Urgency":null,"DrRemark":null,"Newborn":0,"DefaultPatient":1,"CreateTime":"2018-09-27 18:01:31","CredTypeName":"","MaritalStatusName":"","JobName":"","EducationName":"","MedicalTypeName":"","Age":""}
     */

    private int Code;
    private String Message;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * PatientId : 60
         * AccountId : 35
         * PatientName :
         * NameLetter :
         * PatientSex : 3
         * Birthday : null
         * CredType : null
         * CredNo : null
         * Mobile : 13823230159
         * Phone : null
         * PicturePath : http://userimage.newstarthealth.cn/pat/0/60.jpg?dt=201810251345580
         * MaritalStatus : null
         * AreaId : null
         * AreaName : null
         * Region : null
         * PatientType : 0
         * Job : -1
         * Education : -1
         * MedicalType : -1
         * SocialNum : null
         * UrgentPhone : null
         * Urgency : null
         * DrRemark : null
         * Newborn : 0
         * DefaultPatient : 1
         * CreateTime : 2018-09-27 18:01:31
         * CredTypeName :
         * MaritalStatusName :
         * JobName :
         * EducationName :
         * MedicalTypeName :
         * Age :
         */

        private int PatientId;
        private int AccountId;
        private String PatientName;
        private String NameLetter;
        private int PatientSex;
        private Object Birthday;
        private Object CredType;
        private Object CredNo;
        private String Mobile;
        private Object Phone;
        private String PicturePath;
        private Object MaritalStatus;
        private Object AreaId;
        private Object AreaName;
        private Object Region;
        private int PatientType;
        private int Job;
        private int Education;
        private int MedicalType;
        private Object SocialNum;
        private Object UrgentPhone;
        private Object Urgency;
        private Object DrRemark;
        private int Newborn;
        private int DefaultPatient;
        private String CreateTime;
        private String CredTypeName;
        private String MaritalStatusName;
        private String JobName;
        private String EducationName;
        private String MedicalTypeName;
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

        public Object getBirthday() {
            return Birthday;
        }

        public void setBirthday(Object Birthday) {
            this.Birthday = Birthday;
        }

        public Object getCredType() {
            return CredType;
        }

        public void setCredType(Object CredType) {
            this.CredType = CredType;
        }

        public Object getCredNo() {
            return CredNo;
        }

        public void setCredNo(Object CredNo) {
            this.CredNo = CredNo;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public Object getPhone() {
            return Phone;
        }

        public void setPhone(Object Phone) {
            this.Phone = Phone;
        }

        public String getPicturePath() {
            return PicturePath;
        }

        public void setPicturePath(String PicturePath) {
            this.PicturePath = PicturePath;
        }

        public Object getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(Object MaritalStatus) {
            this.MaritalStatus = MaritalStatus;
        }

        public Object getAreaId() {
            return AreaId;
        }

        public void setAreaId(Object AreaId) {
            this.AreaId = AreaId;
        }

        public Object getAreaName() {
            return AreaName;
        }

        public void setAreaName(Object AreaName) {
            this.AreaName = AreaName;
        }

        public Object getRegion() {
            return Region;
        }

        public void setRegion(Object Region) {
            this.Region = Region;
        }

        public int getPatientType() {
            return PatientType;
        }

        public void setPatientType(int PatientType) {
            this.PatientType = PatientType;
        }

        public int getJob() {
            return Job;
        }

        public void setJob(int Job) {
            this.Job = Job;
        }

        public int getEducation() {
            return Education;
        }

        public void setEducation(int Education) {
            this.Education = Education;
        }

        public int getMedicalType() {
            return MedicalType;
        }

        public void setMedicalType(int MedicalType) {
            this.MedicalType = MedicalType;
        }

        public Object getSocialNum() {
            return SocialNum;
        }

        public void setSocialNum(Object SocialNum) {
            this.SocialNum = SocialNum;
        }

        public Object getUrgentPhone() {
            return UrgentPhone;
        }

        public void setUrgentPhone(Object UrgentPhone) {
            this.UrgentPhone = UrgentPhone;
        }

        public Object getUrgency() {
            return Urgency;
        }

        public void setUrgency(Object Urgency) {
            this.Urgency = Urgency;
        }

        public Object getDrRemark() {
            return DrRemark;
        }

        public void setDrRemark(Object DrRemark) {
            this.DrRemark = DrRemark;
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

        public String getMaritalStatusName() {
            return MaritalStatusName;
        }

        public void setMaritalStatusName(String MaritalStatusName) {
            this.MaritalStatusName = MaritalStatusName;
        }

        public String getJobName() {
            return JobName;
        }

        public void setJobName(String JobName) {
            this.JobName = JobName;
        }

        public String getEducationName() {
            return EducationName;
        }

        public void setEducationName(String EducationName) {
            this.EducationName = EducationName;
        }

        public String getMedicalTypeName() {
            return MedicalTypeName;
        }

        public void setMedicalTypeName(String MedicalTypeName) {
            this.MedicalTypeName = MedicalTypeName;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }
    }
}
