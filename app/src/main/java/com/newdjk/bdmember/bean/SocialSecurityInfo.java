package com.newdjk.bdmember.bean;

public class SocialSecurityInfo {

    /**
     * Code : 0
     * Message :
     * Data : {"SocialNum":null,"ImgPath":null}
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
         * SocialNum : null
         * ImgPath : null
         */

        private String SocialNum;
        private String ImgPath;

        public String getSocialNum() {
            return SocialNum;
        }

        public void setSocialNum(String SocialNum) {
            this.SocialNum = SocialNum;
        }

        public String getImgPath() {
            return ImgPath;
        }

        public void setImgPath(String ImgPath) {
            this.ImgPath = ImgPath;
        }
    }
}
