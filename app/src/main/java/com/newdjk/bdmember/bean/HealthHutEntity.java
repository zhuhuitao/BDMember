package com.newdjk.bdmember.bean;

import java.util.List;

public class HealthHutEntity {

    /**
     * Code : 0
     * Message :
     * Data : {"Total":2,"ReturnData":[{"HealthHouseId":1,"HouseImgPath":null,"HealthHouseName":"测试小屋１","SeriveTime":"10:10","HouseAddress":"地址","Description":null,"IsActivity":1},{"HealthHouseId":2,"HouseImgPath":null,"HealthHouseName":"测试小屋２","SeriveTime":"8:10","HouseAddress":"地址２","Description":null,"IsActivity":1}]}
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
         * Total : 2
         * ReturnData : [{"HealthHouseId":1,"HouseImgPath":null,"HealthHouseName":"测试小屋１","SeriveTime":"10:10","HouseAddress":"地址","Description":null,"IsActivity":1},{"HealthHouseId":2,"HouseImgPath":null,"HealthHouseName":"测试小屋２","SeriveTime":"8:10","HouseAddress":"地址２","Description":null,"IsActivity":1}]
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
             * HealthHouseId : 1
             * HouseImgPath : null
             * HealthHouseName : 测试小屋１
             * SeriveTime : 10:10
             * HouseAddress : 地址
             * Description : null
             * IsActivity : 1
             */

            private int HealthHouseId;
            private String HouseImgPath;
            private String HealthHouseName;
            private String SeriveTime;
            private String HouseAddress;
            private String Description;
            private int IsActivity;

            public int getHealthHouseId() {
                return HealthHouseId;
            }

            public void setHealthHouseId(int HealthHouseId) {
                this.HealthHouseId = HealthHouseId;
            }

            public String getHouseImgPath() {
                return HouseImgPath;
            }

            public void setHouseImgPath(String HouseImgPath) {
                this.HouseImgPath = HouseImgPath;
            }

            public String getHealthHouseName() {
                return HealthHouseName;
            }

            public void setHealthHouseName(String HealthHouseName) {
                this.HealthHouseName = HealthHouseName;
            }

            public String getSeriveTime() {
                return SeriveTime;
            }

            public void setSeriveTime(String SeriveTime) {
                this.SeriveTime = SeriveTime;
            }

            public String getHouseAddress() {
                return HouseAddress;
            }

            public void setHouseAddress(String HouseAddress) {
                this.HouseAddress = HouseAddress;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public int getIsActivity() {
                return IsActivity;
            }

            public void setIsActivity(int IsActivity) {
                this.IsActivity = IsActivity;
            }
        }
    }
}
