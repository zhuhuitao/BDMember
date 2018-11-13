package com.newdjk.bdmember.bean;

import java.util.List;

public class PatientRegImgListInfo {

    /**
     * PatientId : 1
     * PatientRegImgImgs : [{"ImgType":64,"ImgPath":"sample string 2","CertNO":"sample string 3"},{"ImgType":64,"ImgPath":"sample string 2","CertNO":"sample string 3"}]
     */

    private int PatientId;
    private List<PatientRegImgImgsInfo> PatientRegImgImgs;

    public int getPatientId() {
        return PatientId;
    }

    public void setPatientId(int PatientId) {
        this.PatientId = PatientId;
    }

    public List<PatientRegImgImgsInfo> getPatientRegImgImgs() {
        return PatientRegImgImgs;
    }

    public void setPatientRegImgImgs(List<PatientRegImgImgsInfo> PatientRegImgImgs) {
        this.PatientRegImgImgs = PatientRegImgImgs;
    }

    public static class PatientRegImgImgsBean {
        /**
         * ImgType : 64
         * ImgPath : sample string 2
         * CertNO : sample string 3
         */

        private int ImgType;
        private String ImgPath;
        private String CertNO;

        public int getImgType() {
            return ImgType;
        }

        public void setImgType(int ImgType) {
            this.ImgType = ImgType;
        }

        public String getImgPath() {
            return ImgPath;
        }

        public void setImgPath(String ImgPath) {
            this.ImgPath = ImgPath;
        }

        public String getCertNO() {
            return CertNO;
        }

        public void setCertNO(String CertNO) {
            this.CertNO = CertNO;
        }
    }
}
