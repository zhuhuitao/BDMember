package com.newdjk.bdmember.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FamilyMedicalTeamEntity {

    /**
     * Code : 0
     * Message :
     * Data : {"Total":1,"ReturnData":[{"Id":1,"AreaId":0,"AreaName":null,"OrgId":0,"TeamName":"家庭医生团队1","SocialHealthCenterId":0,"Phone":null,"IsEnable":0,"CreateTime":"","UpdateTime":"","TeamCaptain":"谭咏麟","DrId":270,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811094002559"}]}
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
         * Total : 1
         * ReturnData : [{"Id":1,"AreaId":0,"AreaName":null,"OrgId":0,"TeamName":"家庭医生团队1","SocialHealthCenterId":0,"Phone":null,"IsEnable":0,"CreateTime":"","UpdateTime":"","TeamCaptain":"谭咏麟","DrId":270,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811094002559"}]
         */

        private int Total;
        private List<ReturnDataBean> ReturnData;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public List<ReturnDataBean> getReturnData() {
            return ReturnData;
        }

        public void setReturnData(List<ReturnDataBean> ReturnData) {
            this.ReturnData = ReturnData;
        }

        public static class ReturnDataBean implements Parcelable {
            /**
             * Id : 1
             * AreaId : 0
             * AreaName : null
             * OrgId : 0
             * TeamName : 家庭医生团队1
             * SocialHealthCenterId : 0
             * Phone : null
             * IsEnable : 0
             * CreateTime :
             * UpdateTime :
             * TeamCaptain : 谭咏麟
             * DrId : 270
             * PicturePath : http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811094002559
             */

            private int Id;
            private int AreaId;
            private String AreaName;
            private int OrgId;
            private String TeamName;
            private int SocialHealthCenterId;
            private String Phone;
            private int IsEnable;
            private String CreateTime;
            private String UpdateTime;
            private String TeamCaptain;
            private int DrId;
            private String PicturePath;

            protected ReturnDataBean(Parcel in) {
                Id = in.readInt();
                AreaId = in.readInt();
                AreaName = in.readString();
                OrgId = in.readInt();
                TeamName = in.readString();
                SocialHealthCenterId = in.readInt();
                Phone = in.readString();
                IsEnable = in.readInt();
                CreateTime = in.readString();
                UpdateTime = in.readString();
                TeamCaptain = in.readString();
                DrId = in.readInt();
                PicturePath = in.readString();
            }

            public static final Creator<ReturnDataBean> CREATOR = new Creator<ReturnDataBean>() {
                @Override
                public ReturnDataBean createFromParcel(Parcel in) {
                    return new ReturnDataBean(in);
                }

                @Override
                public ReturnDataBean[] newArray(int size) {
                    return new ReturnDataBean[size];
                }
            };

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getAreaId() {
                return AreaId;
            }

            public void setAreaId(int AreaId) {
                this.AreaId = AreaId;
            }

            public String getAreaName() {
                return AreaName;
            }

            public void setAreaName(String AreaName) {
                this.AreaName = AreaName;
            }

            public int getOrgId() {
                return OrgId;
            }

            public void setOrgId(int OrgId) {
                this.OrgId = OrgId;
            }

            public String getTeamName() {
                return TeamName;
            }

            public void setTeamName(String TeamName) {
                this.TeamName = TeamName;
            }

            public int getSocialHealthCenterId() {
                return SocialHealthCenterId;
            }

            public void setSocialHealthCenterId(int SocialHealthCenterId) {
                this.SocialHealthCenterId = SocialHealthCenterId;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public int getIsEnable() {
                return IsEnable;
            }

            public void setIsEnable(int IsEnable) {
                this.IsEnable = IsEnable;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public String getTeamCaptain() {
                return TeamCaptain;
            }

            public void setTeamCaptain(String TeamCaptain) {
                this.TeamCaptain = TeamCaptain;
            }

            public int getDrId() {
                return DrId;
            }

            public void setDrId(int DrId) {
                this.DrId = DrId;
            }

            public String getPicturePath() {
                return PicturePath;
            }

            public void setPicturePath(String PicturePath) {
                this.PicturePath = PicturePath;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(Id);
                dest.writeInt(AreaId);
                dest.writeString(AreaName);
                dest.writeInt(OrgId);
                dest.writeString(TeamName);
                dest.writeInt(SocialHealthCenterId);
                dest.writeString(Phone);
                dest.writeInt(IsEnable);
                dest.writeString(CreateTime);
                dest.writeString(UpdateTime);
                dest.writeString(TeamCaptain);
                dest.writeInt(DrId);
                dest.writeString(PicturePath);
            }
        }
    }
}
