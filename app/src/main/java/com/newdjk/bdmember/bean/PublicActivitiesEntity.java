package com.newdjk.bdmember.bean;

import java.util.List;

public class PublicActivitiesEntity {

    /**
     * Code : 0
     * Message :
     * Data : {"Total":6,"ReturnData":[{"Id":1,"Title":"第一个公益活动1","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/a08dd88a-a7a7-4c6b-9ee7-a6317b5f2f2a.png","StartTime":"2018-11-01 00:00:00","Status":1,"Enrollment":2,"Content":null,"DurationMin":8000,"Address":null},{"Id":2,"Title":"第二个公益活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/be58bb15-f16d-44ab-b102-b303a2b256d3.png","StartTime":"2018-11-06 15:05:33","Status":2,"Enrollment":1,"Content":null,"DurationMin":12000,"Address":null},{"Id":3,"Title":"第三个公益活动","MasterMap":null,"StartTime":"2018-11-06 15:06:47","Status":2,"Enrollment":1,"Content":null,"DurationMin":1000,"Address":null},{"Id":4,"Title":"第四个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/e5018b1f-2722-428c-9090-1bdca0b07ba6.jpg","StartTime":"2018-11-09 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":562,"Address":null},{"Id":5,"Title":"第五个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/cd06429e-2b9e-4a3a-85ce-b78b4148ff49.jpg","StartTime":"2018-11-08 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":50,"Address":null},{"Id":6,"Title":"第七个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/bfdb4b6a-0e55-4987-bac4-f3e4538e37dc.jpg","StartTime":"2018-11-09 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":503,"Address":null}]}
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
         * Total : 6
         * ReturnData : [{"Id":1,"Title":"第一个公益活动1","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/a08dd88a-a7a7-4c6b-9ee7-a6317b5f2f2a.png","StartTime":"2018-11-01 00:00:00","Status":1,"Enrollment":2,"Content":null,"DurationMin":8000,"Address":null},{"Id":2,"Title":"第二个公益活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/be58bb15-f16d-44ab-b102-b303a2b256d3.png","StartTime":"2018-11-06 15:05:33","Status":2,"Enrollment":1,"Content":null,"DurationMin":12000,"Address":null},{"Id":3,"Title":"第三个公益活动","MasterMap":null,"StartTime":"2018-11-06 15:06:47","Status":2,"Enrollment":1,"Content":null,"DurationMin":1000,"Address":null},{"Id":4,"Title":"第四个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/e5018b1f-2722-428c-9090-1bdca0b07ba6.jpg","StartTime":"2018-11-09 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":562,"Address":null},{"Id":5,"Title":"第五个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/cd06429e-2b9e-4a3a-85ce-b78b4148ff49.jpg","StartTime":"2018-11-08 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":50,"Address":null},{"Id":6,"Title":"第七个活动","MasterMap":"http://172.18.30.4:8009/HealthHouseActive/bfdb4b6a-0e55-4987-bac4-f3e4538e37dc.jpg","StartTime":"2018-11-09 00:00:00","Status":2,"Enrollment":0,"Content":null,"DurationMin":503,"Address":null}]
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
             * Title : 第一个公益活动1
             * MasterMap : http://172.18.30.4:8009/HealthHouseActive/a08dd88a-a7a7-4c6b-9ee7-a6317b5f2f2a.png
             * StartTime : 2018-11-01 00:00:00
             * Status : 1
             * Enrollment : 2
             * Content : null
             * DurationMin : 8000
             * Address : null
             */

            private int Id;
            private String Title;
            private String MasterMap;
            private String StartTime;
            private int Status;
            private int Enrollment;
            private Object Content;
            private int DurationMin;
            private Object Address;

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

            public String getMasterMap() {
                return MasterMap;
            }

            public void setMasterMap(String MasterMap) {
                this.MasterMap = MasterMap;
            }

            public String getStartTime() {
                return StartTime;
            }

            public void setStartTime(String StartTime) {
                this.StartTime = StartTime;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public int getEnrollment() {
                return Enrollment;
            }

            public void setEnrollment(int Enrollment) {
                this.Enrollment = Enrollment;
            }

            public Object getContent() {
                return Content;
            }

            public void setContent(Object Content) {
                this.Content = Content;
            }

            public int getDurationMin() {
                return DurationMin;
            }

            public void setDurationMin(int DurationMin) {
                this.DurationMin = DurationMin;
            }

            public Object getAddress() {
                return Address;
            }

            public void setAddress(Object Address) {
                this.Address = Address;
            }
        }
    }
}
