package com.fubang.fubangzhibo;

import android.app.Application;
import android.util.Log;

import com.fubang.fubangzhibo.utils.DbUtil;
import com.xlg.android.video.AVModuleMgr;
import com.zhuyunjian.library.FrescoHelper;

import org.androidannotations.annotations.EApplication;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dell on 2016/4/5.
 */
@EApplication
public class App extends Application{
//    private AVModuleMgr mgr ;
//    private static volatile App instance = null;
//
//    // private constructor suppresses
//    private App(){
//        mgr = AVModuleMgr.getInstance();
//    }
//
//    public static App getInstance() {
//        // if already inited, no need to get lock everytime
//        if (instance == null) {
//            synchronized (App.class) {
//                if (instance == null) {
//                    instance = new App();
//                }
//            }
//        }
//
//        return instance;
//    }

    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
//        setMgr(new AVModuleMgr());
//        Log.d("123",mgr+"------mgr");
        //初始化Fresco
        FrescoHelper.getInstance().init(this);
        //初始化数据库类
        DbUtil.init(this);
        //初始化ShareSDK
        ShareSDK.initSDK(this);
    }
}
