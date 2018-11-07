package com.newdjk.bdmember.bean;

import java.util.List;

public class HealthGovernmentEntity {


    /**
     * Code : 0
     * Message :
     * Data : {"Total":1,"ReturnData":[{"Id":1,"Title":"健康资讯１","CategoryId":1,"OrgId":1,"MasterMap":null,"Content":"健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯","OrderNo":1,"CreateId":1,"CreateTime":"2018-11-06 16:21:36","UpdateId":1,"UpdateTime":"2018-11-06 16:21:36","IsEnable":1,"Invalid":0}]}
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
         * ReturnData : [{"Id":1,"Title":"健康资讯１","CategoryId":1,"OrgId":1,"MasterMap":null,"Content":"健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯","OrderNo":1,"CreateId":1,"CreateTime":"2018-11-06 16:21:36","UpdateId":1,"UpdateTime":"2018-11-06 16:21:36","IsEnable":1,"Invalid":0}]
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

        public static class ReturnDataBean {
            /**
             * Id : 1
             * Title : 健康资讯１
             * CategoryId : 1
             * OrgId : 1
             * MasterMap : null
             * Content : 健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯健康资讯
             * OrderNo : 1
             * CreateId : 1
             * CreateTime : 2018-11-06 16:21:36
             * UpdateId : 1
             * UpdateTime : 2018-11-06 16:21:36
             * IsEnable : 1
             * Invalid : 0
             */

            private int Id;
            private String Title;
            private int CategoryId;
            private int OrgId;
            private String MasterMap;
            private String Content;
            private int OrderNo;
            private int CreateId;
            private String CreateTime;
            private int UpdateId;
            private String UpdateTime;
            private int IsEnable;
            private int Invalid;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public int getCategoryId() {
                return CategoryId;
            }

            public void setCategoryId(int CategoryId) {
                this.CategoryId = CategoryId;
            }

            public int getOrgId() {
                return OrgId;
            }

            public void setOrgId(int OrgId) {
                this.OrgId = OrgId;
            }

            public String getMasterMap() {
                return MasterMap;
            }

            public void setMasterMap(String MasterMap) {
                this.MasterMap = MasterMap;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String Content) {
                this.Content = Content;
            }

            public int getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(int OrderNo) {
                this.OrderNo = OrderNo;
            }

            public int getCreateId() {
                return CreateId;
            }

            public void setCreateId(int CreateId) {
                this.CreateId = CreateId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getUpdateId() {
                return UpdateId;
            }

            public void setUpdateId(int UpdateId) {
                this.UpdateId = UpdateId;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public int getIsEnable() {
                return IsEnable;
            }

            public void setIsEnable(int IsEnable) {
                this.IsEnable = IsEnable;
            }

            public int getInvalid() {
                return Invalid;
            }

            public void setInvalid(int Invalid) {
                this.Invalid = Invalid;
            }
        }
    }
}
