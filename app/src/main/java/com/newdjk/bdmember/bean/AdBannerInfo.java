package com.newdjk.bdmember.bean;

import java.util.List;

/**
 * Created by EDZ on 2018/9/19.
 */

public class AdBannerInfo {


    /**
     * Code : 0
     * Message :
     * Data : [{"AdClassName":"芸医生首页","Id":1,"AdClassId":1,"Name":"1","ContentType":0,"ContentLink":null,"Content":"<p><img src=\"/QustionBankImages/image/20151109/6358267710072792366523685.jpg\" title=\"1.jpg\" alt=\"1.jpg\"/>ce<\/p>","Sort":1,"EffectiveTime":"2005-05-05 00:00:00","Status":1,"CreateTime":"2015-11-09 13:41:19"},{"AdClassName":"芸医生首页","Id":29,"AdClassId":1,"Name":"ce","ContentType":2,"ContentLink":"eee","Content":"http://localhost:5297/aps/image/20180918/6367289410992259785183635.png","Sort":1,"EffectiveTime":"2018-09-27 00:00:00","Status":1,"CreateTime":"2018-09-18 19:01:55"},{"AdClassName":"芸医生首页","Id":30,"AdClassId":1,"Name":"banner2","ContentType":2,"ContentLink":"www.baidu.com","Content":"http://localhost:5297/aps/image/20180919/6367294807913047266917189.png","Sort":1,"EffectiveTime":"2018-09-29 00:00:00","Status":1,"CreateTime":"2018-09-19 09:39:24"},{"AdClassName":"芸医生首页","Id":31,"AdClassId":1,"Name":"ce","ContentType":2,"ContentLink":"www.01.com","Content":"http://localhost:5297/aps/image/20180919/6367295141695941015491673.png","Sort":1,"EffectiveTime":"2018-09-28 00:00:00","Status":1,"CreateTime":"2018-09-19 09:55:29"},{"AdClassName":"芸医生首页","Id":32,"AdClassId":1,"Name":"交流电","ContentType":2,"ContentLink":"www.newdjk.com","Content":"http://localhost:5297/aps/image/20180919/6367295151273630511735421.gif","Sort":1,"EffectiveTime":"2018-10-06 00:00:00","Status":1,"CreateTime":"2018-09-19 10:58:53"},{"AdClassName":"芸医生首页","Id":33,"AdClassId":1,"Name":"热门护士","ContentType":2,"ContentLink":"www.waitting.com","Content":"http://localhost:5297/ad/image/20180919/6367295213643768723857042.gif","Sort":1,"EffectiveTime":"2019-09-12 00:00:00","Status":1,"CreateTime":"2018-09-19 11:09:29"}]
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
         * AdClassName : 芸医生首页
         * Id : 1
         * AdClassId : 1
         * Name : 1
         * ContentType : 0
         * ContentLink : null
         * Content : <p><img src="/QustionBankImages/image/20151109/6358267710072792366523685.jpg" title="1.jpg" alt="1.jpg"/>ce</p>
         * Sort : 1
         * EffectiveTime : 2005-05-05 00:00:00
         * Status : 1
         * CreateTime : 2015-11-09 13:41:19
         */

        private String AdClassName;
        private int Id;
        private int AdClassId;
        private String Name;
        private int ContentType;
        private String ContentLink;
        private String Content;
        private int Sort;
        private String EffectiveTime;
        private int Status;
        private String CreateTime;

        public String getAdClassName() {
            return AdClassName;
        }

        public void setAdClassName(String AdClassName) {
            this.AdClassName = AdClassName;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getAdClassId() {
            return AdClassId;
        }

        public void setAdClassId(int AdClassId) {
            this.AdClassId = AdClassId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getContentType() {
            return ContentType;
        }

        public void setContentType(int ContentType) {
            this.ContentType = ContentType;
        }

        public String getContentLink() {
            return ContentLink;
        }

        public void setContentLink(String ContentLink) {
            this.ContentLink = ContentLink;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public String getEffectiveTime() {
            return EffectiveTime;
        }

        public void setEffectiveTime(String EffectiveTime) {
            this.EffectiveTime = EffectiveTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
