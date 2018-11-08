package com.newdjk.bdmember.bean;

import java.util.List;

public class AreaEntity {

    /**
     * Code : 0
     * Message :
     * Data : [{"AreaId":496,"AreaName":"市辖区","AreaCode":"130601","AreaParentCode":"130600","ParentId":42,"Path":",0,3,42,496,","AreaOrder":0,"IsHot":0,"EnglishName":""}]
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
         * AreaId : 496
         * AreaName : 市辖区
         * AreaCode : 130601
         * AreaParentCode : 130600
         * ParentId : 42
         * Path : ,0,3,42,496,
         * AreaOrder : 0
         * IsHot : 0
         * EnglishName :
         */

        private int AreaId;
        private String AreaName;
        private String AreaCode;
        private String AreaParentCode;
        private int ParentId;
        private String Path;
        private int AreaOrder;
        private int IsHot;
        private String EnglishName;

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

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String AreaCode) {
            this.AreaCode = AreaCode;
        }

        public String getAreaParentCode() {
            return AreaParentCode;
        }

        public void setAreaParentCode(String AreaParentCode) {
            this.AreaParentCode = AreaParentCode;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public int getAreaOrder() {
            return AreaOrder;
        }

        public void setAreaOrder(int AreaOrder) {
            this.AreaOrder = AreaOrder;
        }

        public int getIsHot() {
            return IsHot;
        }

        public void setIsHot(int IsHot) {
            this.IsHot = IsHot;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }
}
