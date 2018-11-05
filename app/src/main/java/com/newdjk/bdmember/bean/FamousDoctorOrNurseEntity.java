package com.newdjk.bdmember.bean;

import java.util.List;

/**
 * Created by EDZ on 2018/9/17.
 */

public class FamousDoctorOrNurseEntity {


    /**
     * Code : 0
     * Message :
     * Data : [{"DrId":1,"DrName":"欧阳云生","Mobile":"13800138000","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科1","Position":4,"PositionName":"住院医师","DoctorGroup":"sample string 9","PicturePath":"http://172.18.30.4:8006/doc/0/1.jpg","TreatMent":"sample string 11","DoctorSkill":"sample string 12","Description":null,"IsOnline":1,"CreateTime":"2018-08-29 10:01:46","PraiseRate":100,"ConsultCount":88,"ResponseRate":77,"ConsultVolume":99,"OnPrescription":0,"VisitCount":0,"Telenursing":88,"VolumeVideo":99,"FetalHeart":0,"SericeItemCodes":"","DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0},{"DrId":2,"DrName":"郭旭","Mobile":"17546893247","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科1","Position":1,"PositionName":"主任医师","DoctorGroup":null,"PicturePath":"http://172.18.30.4:8006/doc/0/2.jpg","TreatMent":"妇科疾病","DoctorSkill":"宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎宫颈炎","Description":"从业多年，临床经验丰富","IsOnline":1,"CreateTime":"2018-08-30 11:59:06","PraiseRate":95,"ConsultCount":77,"ResponseRate":65,"ConsultVolume":88,"OnPrescription":13,"VisitCount":14,"Telenursing":87,"VolumeVideo":88,"FetalHeart":16,"SericeItemCodes":"1101|图文问诊,1102|视频问诊,1103|名医续方","DrServiceItems":null,"IsPatientMain":false,"IsHasPack":1},{"DrId":3,"DrName":"刘霞","Mobile":"17715163247","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科1","Position":1,"PositionName":"主任医师","DoctorGroup":null,"PicturePath":null,"TreatMent":"妇科疾病","DoctorSkill":"宫颈炎","Description":"测试数据","IsOnline":1,"CreateTime":"2018-08-30 13:40:37","PraiseRate":90,"ConsultCount":66,"ResponseRate":44,"ConsultVolume":77,"OnPrescription":0,"VisitCount":0,"Telenursing":86,"VolumeVideo":77,"FetalHeart":0,"SericeItemCodes":"","DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0},{"DrId":10,"DrName":"王恒","Mobile":"123457896544","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科1","Position":4,"PositionName":"住院医师","DoctorGroup":"5","PicturePath":"http://172.18.30.4:8006/doc/0/10.jpg","TreatMent":"11","DoctorSkill":"111","Description":"测试数据","IsOnline":1,"CreateTime":"2018-09-05 19:26:33","PraiseRate":43,"ConsultCount":11,"ResponseRate":22,"ConsultVolume":44,"OnPrescription":0,"VisitCount":0,"Telenursing":67,"VolumeVideo":43,"FetalHeart":0,"SericeItemCodes":null,"DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0},{"DrId":11,"DrName":"王恒","Mobile":"123457896544","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科1","Position":5,"PositionName":"医师","DoctorGroup":"5","PicturePath":null,"TreatMent":null,"DoctorSkill":"111","Description":"测试数据","IsOnline":1,"CreateTime":"2018-09-05 19:28:24","PraiseRate":23,"ConsultCount":10,"ResponseRate":12,"ConsultVolume":32,"OnPrescription":0,"VisitCount":0,"Telenursing":67,"VolumeVideo":55,"FetalHeart":0,"SericeItemCodes":null,"DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0},{"DrId":13,"DrName":"刘凤霞","Mobile":"17715163247","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科2","Position":2,"PositionName":"副主任医师","DoctorGroup":null,"PicturePath":"http://172.18.30.4:8006/doc/0/13.jpg","TreatMent":"妇科疾病","DoctorSkill":"宫颈炎","Description":"测试数据","IsOnline":1,"CreateTime":"2018-09-05 20:16:23","PraiseRate":12,"ConsultCount":8,"ResponseRate":13,"ConsultVolume":22,"OnPrescription":0,"VisitCount":0,"Telenursing":0,"VolumeVideo":12,"FetalHeart":0,"SericeItemCodes":null,"DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0},{"DrId":14,"DrName":"刘凤霞","Mobile":"17715163247","DrType":1,"HospitalName":"人民医院","DepartmentName":"内科2","Position":6,"PositionName":"医士","DoctorGroup":null,"PicturePath":"http://172.18.30.4:8006/doc/0/14.jpg","TreatMent":"妇科疾病","DoctorSkill":"宫颈炎","Description":"测试数据","IsOnline":1,"CreateTime":"2018-09-05 20:16:23","PraiseRate":34,"ConsultCount":7,"ResponseRate":11,"ConsultVolume":11,"OnPrescription":0,"VisitCount":0,"Telenursing":0,"VolumeVideo":98,"FetalHeart":0,"SericeItemCodes":null,"DrServiceItems":null,"IsPatientMain":false,"IsHasPack":0}]
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
         * DrId : 1
         * DrName : 欧阳云生
         * Mobile : 13800138000
         * DrType : 1
         * HospitalName : 人民医院
         * DepartmentName : 内科1
         * Position : 4
         * PositionName : 住院医师
         * DoctorGroup : sample string 9
         * PicturePath : http://172.18.30.4:8006/doc/0/1.jpg
         * TreatMent : sample string 11
         * DoctorSkill : sample string 12
         * Description : null
         * IsOnline : 1
         * CreateTime : 2018-08-29 10:01:46
         * PraiseRate : 100
         * ConsultCount : 88
         * ResponseRate : 77
         * ConsultVolume : 99
         * OnPrescription : 0
         * VisitCount : 0
         * Telenursing : 88
         * VolumeVideo : 99
         * FetalHeart : 0
         * SericeItemCodes :
         * DrServiceItems : null
         * IsPatientMain : false
         * IsHasPack : 0
         */

        private int DrId;
        private String DrName;
        private String Mobile;
        private int DrType;
        private String HospitalName;
        private String DepartmentName;
        private int Position;
        private String PositionName;
        private String DoctorGroup;
        private String PicturePath;
        private String TreatMent;
        private String DoctorSkill;
        private Object Description;
        private int IsOnline;
        private String CreateTime;
        private int PraiseRate;
        private int ConsultCount;
        private int ResponseRate;
        private int ConsultVolume;
        private int OnPrescription;
        private int VisitCount;
        private int Telenursing;
        private int VolumeVideo;
        private int FetalHeart;
        private String SericeItemCodes;
        private Object DrServiceItems;
        private boolean IsPatientMain;
        private int IsHasPack;

        public int getDrId() {
            return DrId;
        }

        public void setDrId(int DrId) {
            this.DrId = DrId;
        }

        public String getDrName() {
            return DrName;
        }

        public void setDrName(String DrName) {
            this.DrName = DrName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public int getDrType() {
            return DrType;
        }

        public void setDrType(int DrType) {
            this.DrType = DrType;
        }

        public String getHospitalName() {
            return HospitalName;
        }

        public void setHospitalName(String HospitalName) {
            this.HospitalName = HospitalName;
        }

        public String getDepartmentName() {
            return DepartmentName;
        }

        public void setDepartmentName(String DepartmentName) {
            this.DepartmentName = DepartmentName;
        }

        public int getPosition() {
            return Position;
        }

        public void setPosition(int Position) {
            this.Position = Position;
        }

        public String getPositionName() {
            return PositionName;
        }

        public void setPositionName(String PositionName) {
            this.PositionName = PositionName;
        }

        public String getDoctorGroup() {
            return DoctorGroup;
        }

        public void setDoctorGroup(String DoctorGroup) {
            this.DoctorGroup = DoctorGroup;
        }

        public String getPicturePath() {
            return PicturePath;
        }

        public void setPicturePath(String PicturePath) {
            this.PicturePath = PicturePath;
        }

        public String getTreatMent() {
            return TreatMent;
        }

        public void setTreatMent(String TreatMent) {
            this.TreatMent = TreatMent;
        }

        public String getDoctorSkill() {
            return DoctorSkill;
        }

        public void setDoctorSkill(String DoctorSkill) {
            this.DoctorSkill = DoctorSkill;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public int getIsOnline() {
            return IsOnline;
        }

        public void setIsOnline(int IsOnline) {
            this.IsOnline = IsOnline;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getPraiseRate() {
            return PraiseRate;
        }

        public void setPraiseRate(int PraiseRate) {
            this.PraiseRate = PraiseRate;
        }

        public int getConsultCount() {
            return ConsultCount;
        }

        public void setConsultCount(int ConsultCount) {
            this.ConsultCount = ConsultCount;
        }

        public int getResponseRate() {
            return ResponseRate;
        }

        public void setResponseRate(int ResponseRate) {
            this.ResponseRate = ResponseRate;
        }

        public int getConsultVolume() {
            return ConsultVolume;
        }

        public void setConsultVolume(int ConsultVolume) {
            this.ConsultVolume = ConsultVolume;
        }

        public int getOnPrescription() {
            return OnPrescription;
        }

        public void setOnPrescription(int OnPrescription) {
            this.OnPrescription = OnPrescription;
        }

        public int getVisitCount() {
            return VisitCount;
        }

        public void setVisitCount(int VisitCount) {
            this.VisitCount = VisitCount;
        }

        public int getTelenursing() {
            return Telenursing;
        }

        public void setTelenursing(int Telenursing) {
            this.Telenursing = Telenursing;
        }

        public int getVolumeVideo() {
            return VolumeVideo;
        }

        public void setVolumeVideo(int VolumeVideo) {
            this.VolumeVideo = VolumeVideo;
        }

        public int getFetalHeart() {
            return FetalHeart;
        }

        public void setFetalHeart(int FetalHeart) {
            this.FetalHeart = FetalHeart;
        }

        public String getSericeItemCodes() {
            return SericeItemCodes;
        }

        public void setSericeItemCodes(String SericeItemCodes) {
            this.SericeItemCodes = SericeItemCodes;
        }

        public Object getDrServiceItems() {
            return DrServiceItems;
        }

        public void setDrServiceItems(Object DrServiceItems) {
            this.DrServiceItems = DrServiceItems;
        }

        public boolean isIsPatientMain() {
            return IsPatientMain;
        }

        public void setIsPatientMain(boolean IsPatientMain) {
            this.IsPatientMain = IsPatientMain;
        }

        public int getIsHasPack() {
            return IsHasPack;
        }

        public void setIsHasPack(int IsHasPack) {
            this.IsHasPack = IsHasPack;
        }
    }
}
