package com.newdjk.bdmember;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lxq.okhttp.MyOkHttp;
import com.lxq.okhttp.demo.DownloadMgr;
import com.lxq.okhttp.persistentcookiejar.ClearableCookieJar;
import com.lxq.okhttp.persistentcookiejar.PersistentCookieJar;
import com.lxq.okhttp.persistentcookiejar.cache.SetCookieCache;
import com.lxq.okhttp.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.lxq.okhttp.utils.OkHttpLogUtils;
import com.newdjk.bdmember.scan.activity.ZXingLibrary;
import com.newdjk.bdmember.utils.MainConstant;
import com.newdjk.bdmember.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends Application {
    private MyOkHttp mMyOkHttp;
    private DownloadMgr mDownloadMgr;
    private static MyApplication application;
    private static int mainTid;
    private static Context context;
    public static boolean LOG_DEBUG = true;//输出日志工具类
    public static IWXAPI mWxApi;
    public String accountId;
    public static String mRegistrationId;
    @Override
    public void onCreate() {
        super.onCreate();
        initDate();
        initOkHttp();
        registToWX();
        initJpush();
    }




    private void initDate() {
        application = this;
        context = this;
        mainTid = android.os.Process.myTid();
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initOkHttp() {
        OkHttpLogUtils.isDebug = LOG_DEBUG;
        //持久化存储cookie
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        //log拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //自定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)       //设置开启cookie
                .addInterceptor(logging)            //设置开启log
                .build();
        mMyOkHttp = new MyOkHttp(okHttpClient);

        mDownloadMgr = (DownloadMgr) new DownloadMgr.Builder()
                .myOkHttp(mMyOkHttp)
                .maxDownloadIngNum(5)       //设置最大同时下载数量（不设置默认5）
                .saveProgressBytes(50 * 1204)   //设置每50kb触发一次saveProgress保存进度 （不能在onProgress每次都保存 过于频繁） 不设置默认50kb
                .build();

        mDownloadMgr.resumeTasks();     //恢复本地所有未完成的任务
    }

    /**
     *  注册微信
     */
    private void registToWX() {
        mWxApi = WXPayEntryActivity.initWeiXin(this, MainConstant.WEIXIN_APP_ID);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initJpush() {
        mRegistrationId = JPushInterface.getRegistrationID(this);
    }

    public static MyApplication getInstance() {
        return application;
    }

    public MyOkHttp getmMyOkHttp() {
        return mMyOkHttp;
    }

    public void setmMyOkHttp(MyOkHttp mMyOkHttp) {
        this.mMyOkHttp = mMyOkHttp;
    }

    public DownloadMgr getmDownloadMgr() {
        return mDownloadMgr;
    }

    public void setmDownloadMgr(DownloadMgr mDownloadMgr) {
        this.mDownloadMgr = mDownloadMgr;
    }

    public static MyApplication getApplication() {
        return application;
    }

    public static void setApplication(MyApplication application) {
        MyApplication.application = application;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static void setMainTid(int mainTid) {
        MyApplication.mainTid = mainTid;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public static boolean isLogDebug() {
        return LOG_DEBUG;
    }

    public static void setLogDebug(boolean logDebug) {
        LOG_DEBUG = logDebug;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
