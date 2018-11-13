package com.newdjk.bdmember.bean;

import java.util.List;

public class FamilyMedicalDetailInfo {

    /**
     * Code : 0
     * Message :
     * Data : {"Id":1,"TeamCaptain":"谭咏麟","DrId":270,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811102624454","Position":"主任医师","Phone":"1895997469959","TeamDesc":"家庭医生团队1家庭医生团队1家庭医生团队1","TeamName":"家庭医生团队1","FamilyDrTeamMembers":[{"Id":1,"FDrTeamId":1,"DrId":270,"DrName":"谭咏麟","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811102624454","PositionName":"主任医师","Position":1,"IsCaptain":1,"SocialhealthCenter":null},{"Id":2,"FDrTeamId":1,"DrId":271,"DrName":"龙虾","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/271.jpg?dt=201811102624457","PositionName":"主任医师","Position":1,"IsCaptain":0,"SocialhealthCenter":null},{"Id":3,"FDrTeamId":1,"DrId":272,"DrName":"艾勇","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/272.jpg?dt=201811102624460","PositionName":"主任医师","Position":1,"IsCaptain":0,"SocialhealthCenter":null}]}
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
         * Id : 1
         * TeamCaptain : 谭咏麟
         * DrId : 270
         * PicturePath : http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811102624454
         * Position : 主任医师
         * Phone : 1895997469959
         * TeamDesc : 家庭医生团队1家庭医生团队1家庭医生团队1
         * TeamName : 家庭医生团队1
         * FamilyDrTeamMembers : [{"Id":1,"FDrTeamId":1,"DrId":270,"DrName":"谭咏麟","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811102624454","PositionName":"主任医师","Position":1,"IsCaptain":1,"SocialhealthCenter":null},{"Id":2,"FDrTeamId":1,"DrId":271,"DrName":"龙虾","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/271.jpg?dt=201811102624457","PositionName":"主任医师","Position":1,"IsCaptain":0,"SocialhealthCenter":null},{"Id":3,"FDrTeamId":1,"DrId":272,"DrName":"艾勇","DrType":1,"PicturePath":"http://userimage.newstarthealth.cn/doc/0/272.jpg?dt=201811102624460","PositionName":"主任医师","Position":1,"IsCaptain":0,"SocialhealthCenter":null}]
         */

        private int Id;
        private String TeamCaptain;
        private int DrId;
        private String PicturePath;
        private String Position;
        private String Phone;
        private String TeamDesc;
        private String TeamName;
        private List<FamilyDrTeamMembersBean> FamilyDrTeamMembers;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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

        public String getPosition() {
            return Position;
        }

        public void setPosition(String Position) {
            this.Position = Position;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getTeamDesc() {
            return TeamDesc;
        }

        public void setTeamDesc(String TeamDesc) {
            this.TeamDesc = TeamDesc;
        }

        public String getTeamName() {
            return TeamName;
        }

        public void setTeamName(String TeamName) {
            this.TeamName = TeamName;
        }

        public List<FamilyDrTeamMembersBean> getFamilyDrTeamMembers() {
            return FamilyDrTeamMembers;
        }

        public void setFamilyDrTeamMembers(List<FamilyDrTeamMembersBean> FamilyDrTeamMembers) {
            this.FamilyDrTeamMembers = FamilyDrTeamMembers;
        }

        public static class FamilyDrTeamMembersBean {
            /**
             * Id : 1
             * FDrTeamId : 1
             * DrId : 270
             * DrName : 谭咏麟
             * DrType : 1
             * PicturePath : http://userimage.newstarthealth.cn/doc/0/270.jpg?dt=201811102624454
             * PositionName : 主任医师
             * Position : 1
             * IsCaptain : 1
             * SocialhealthCenter : null
             */

            private int Id;
            private int FDrTeamId;
            private int DrId;
            private String DrName;
            private int DrType;
            private String PicturePath;
            private String PositionName;
            private int Position;
            private int IsCaptain;
            private Object SocialhealthCenter;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getFDrTeamId() {
                return FDrTeamId;
            }

            public void setFDrTeamId(int FDrTeamId) {
                this.FDrTeamId = FDrTeamId;
            }

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

            public int getDrType() {
                return DrType;
            }

            public void setDrType(int DrType) {
                this.DrType = DrType;
            }

            public String getPicturePath() {
                return PicturePath;
            }

            public void setPicturePath(String PicturePath) {
                this.PicturePath = PicturePath;
            }

            public String getPositionName() {
                return PositionName;
            }

            public void setPositionName(String PositionName) {
                this.PositionName = PositionName;
            }

            public int getPosition() {
                return Position;
            }

            public void setPosition(int Position) {
                this.Position = Position;
            }

            public int getIsCaptain() {
                return IsCaptain;
            }

            public void setIsCaptain(int IsCaptain) {
                this.IsCaptain = IsCaptain;
            }

            public Object getSocialhealthCenter() {
                return SocialhealthCenter;
            }

            public void setSocialhealthCenter(Object SocialhealthCenter) {
                this.SocialhealthCenter = SocialhealthCenter;
            }
        }
    }
}
