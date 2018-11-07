package com.newdjk.bdmember.utils;

public class HttpUrl {
    //18702072346   adp
    //public static String ip = "http://172.18.30.4";//？测试环境
    public static String ip = "http://api.newstarthealth.cn";//？正式环境


    public static String ConsultWebAPI = ip + "/ConsultWebAPI";// //1、咨询、复诊：
    public static String PlatFormAPI = ip + "/PlatFormAPI";//	平台接口	包含用户、病人、角色权限、服务包以及知识库等各公共模块接口
    public static String ConsultAPI = ip + "/ConsultAPI";//		咨询、复诊接口	和咨询以及复诊相关接口
    public static String FetalHeartAPI = ip + "/FetalHeartAPI";//		母胎接口	和胎心监护相关接口
    public static String Prescription = ip + "/Prescription";//		处方接口	处方相关接口
    public static String SSOAPI = ip + "/SSOAPI";//		医生  SSO接口	登录以及接口授权认证相关接口
    public static String ContinueServiceAPI = ip + "/ContinueServiceAPI";//		延续服务接口	延续服务接口
    public static String IMAPI = ip + "/IMAPI";//		获取IM信息接口
    public static String Doctor = PlatFormAPI + "/Doctor";//		获取IM信息接口
    public static String Banner = ip + "/AdAPI/";//		获取首页的banner数据
    public static String Area = PlatFormAPI + "/Area/";//获取地区

    //========================登陸===========================
    public static String login = SSOAPI + "/Patient/Login";//登录
    public static String LoginSendMobileCode = PlatFormAPI + "/PatientAccount/SendMobileCode";//登录f发送验证码
    public static String DoctorLogin = SSOAPI + "/Doctor/Login";//登录医生
    public static String loginIm = IMAPI + "/Account/GetIMAccount";//获取IM用户名和签名
    public static String updatePhoto = PlatFormAPI + "/PatientAccount/PatientImagUpload";//会员上传图片
    public static String KnowledgeBase = PlatFormAPI + "/KnowledgeBase";//		知识库(宣教、问卷)
    // ===========================  PatientAccount  ( 患者 )============================================
    public static String PatientArchives = PlatFormAPI + "/PatientArchives";//		健康档案
    public static String PatientAccount = PlatFormAPI + "/PatientAccount";
    //    POST PatientAccount/SendMobileCode 发送会员手机验证码
    public static String SendMobileCode = PatientAccount + "/SendMobileCode";
    //    POST PatientAccount/VerifyMobileCode  验证会员手机验证码(注册验证)
    public static String VerifyMobileCode = PatientAccount + "/VerifyMobileCode";
    //    POST PatientAccount/RegistPatient  注册会员
    public static String RegistPatient = PatientAccount + "/RegistPatient";
    public static String WechatRegist = PatientAccount + "/AppRegistPatient";
    //    POST PatientAccount/QueryPatientAccountByMobileCode 根据手机号码和验证码获取患者信息
    public static String QueryPatientAccountByMobileCode = PatientAccount + "/QueryPatientAccountByMobileCode";
    //    POST PatientAccount/ChangePatientAccountPassword 修改患者密码
    public static String ChangePatientAccountPassword = PatientAccount + "/ChangePatientAccountPassword";
    //    GET PatientAccount/QueryPatientInfoByPatientId?PatientId={PatientId}    根据会员Id获取患者信息
    public static String QueryPatientInfoByPatientId = PatientAccount + "/QueryPatientInfoByPatientId";
    //    GET PatientAccount/UpdatePatientDrRemark?PatientId={PatientId}&DrRemark={DrRemark}修改会员信息的医生备注
    public static String UpdatePatientDrRemark = PatientAccount + "/UpdatePatientDrRemark";
    //    POST PatientAccount/PatientImagUpload 会员证照\头像上传
    public static String PatientImagUpload = PatientAccount + "/PatientImagUpload";
    //    POST PatientAccount/PatientImagSave   会员证照保存(批量保存)
    public static String PatientImagSave = PatientAccount + "/PatientImagSave";
    //    GET PatientAccount/QueryPatientRegImagByDrId?PatientId={PatientId}根据会员Id获取证照
    public static String QueryPatientRegImagByDrId = PatientAccount + "/QueryPatientRegImagByDrId";
    //    GET PatientAccount/DeletePatientRegImag?PatientImgId={PatientImgId} 根据主键Id删除患者证照
    public static String DeletePatientRegImag = PatientAccount + "/DeletePatientRegImag";
    //    GET PatientAccount/QueryAddressByPatientId?PatientId={PatientId} 根据会员Id获取地址列表
    public static String QueryAddressByPatientId = PatientAccount + "/QueryAddressByPatientId";
    //    GET PatientAccount/DeletePatientAddress?PatientAddId={  PatientAddId }根据主键Id删除患者地址
    public static String DeletePatientAddress = PatientAccount + "/DeletePatientAddress";
    //    POST PatientAccount/SavePatientAddress  保存会员地址信息(新增或者修改)
    public static String SavePatientAddress = PatientAccount + "/SavePatientAddress";
    //    GET PatientAccount/SetDefaultAddress?PatientAddId={PatientAddId }&PatientId={PatientId}根据主键Id和会员Id设置患者默认地址
    public static String SetDefaultAddress = PatientAccount + "/SetDefaultAddress";
    //    POST PatientAccount/AddPatientAddress 保存意见与反馈
    public static String AddPatientAddress = PatientAccount + "/AddPatientAddress";
    //    POST PatientAccount/QueryCouponRecordPage     查询患者优惠券
    public static String QueryCouponRecordPage = PatientAccount + "/QueryCouponRecordPage";
    //    GET PatientAccount/QueryPatientVisitByPatientId?AccountId={AccountId}根据账号Id获取就诊人列表
    public static String QueryPatientVisitByPatientId = PatientAccount + "/QueryPatientVisitByPatientId";
    //    GET PatientAccount/DeletePatientVisit?PatientId={PatientId} 根据主键Id删除就诊人
    public static String DeletePatientVisit = PatientAccount + "/DeletePatientVisit";
    //    POST PatientAccount/SavePatientVisit 保存会员就诊人信息(新增或者修改)
    public static String SavePatientVisit = PatientAccount + "/SavePatientVisit";
    //公益活动
    public static String PublicWelfareActivity = PlatFormAPI + "/PublicWelfareActivity";
    //健康政务
    public static String HealthInformation = PlatFormAPI + "/HealthInformation";
    //签约服务
    public static String ServicePackItem = PlatFormAPI + "/ServicePackItem";

    //首页
    public static String QueryDoctorInfoForHot = Doctor + "/QueryDoctorInfoForHot";
    //首页的ad
    public static String QueryAdBannerInfo = Banner + "/Ads/QueryAdsList";
    //修改登陆的手机号码
    public static String ModifyMobile = PlatFormAPI + "/PatientAccount/ChangeAccountMobile";

    public static String ModifyPassword = PlatFormAPI + "/PatientAccount/ChangePatientPasswordByMobile";

    //意见与反馈图片上传
    public static String FeedbackImgLoad = PlatFormAPI + "/DoctorPatient/FeedBackImgUpload";

    //根据微信授权登录OpenId和AppId获取账号信息
    public static String QueryLoginAccount = PlatFormAPI + "/PatientAccount/QueryLoginAccountByOpenId";
    //保存意见与反馈
    public static String AddFeedBack = PlatFormAPI + "/DoctorPatient/AddFeedBack";
    //关于我们
    public static String GetAboutInfo = KnowledgeBase + "/GetAboutInfo";

    //病历
    public static String ReportImageUpload = PatientArchives + "/ReportImageUpload"; //患者病历上传
    //get Area
    public static String QueryAreaByParentId = Area + "QueryAreaByParentId";
    // get hot city
    public static String QueryHostArea = Area + "QueryHostArea";

    //获取公益活动列表
    public static String GetPublicWelfareActivitys = PublicWelfareActivity + "/GetPublicWelfareActivitys";
    //获取健康政务列表
    public static String GetHealthInformationList = HealthInformation + "/GetHealthInformationList";
    //获取签约服务列表
    public static String QueryServicePackPageAndDetail = ServicePackItem + "/QueryServicePackPageAndDetail";

    /**************************************胎心功能****************************************/
    //获取默认就诊人(根据账号Id)
    public static String DefaultPatient = PatientAccount + "/QueryDefaultPatientByAccountId";

}
