package com.fubang.fubangzhibo;

import android.app.Application;

import com.fubang.fubangzhibo.utils.DbUtil;
import com.zhuyunjian.library.FrescoHelper;

import org.androidannotations.annotations.EApplication;

import cn.sharesdk.framework.ShareSDK;
import io.vov.vitamio.Vitamio;

/**
 * Created by dell on 2016/4/5.
 */
@EApplication
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FrescoHelper.getInstance().init(this);
        DbUtil.init(this);
        Vitamio.isInitialized(this);
        ShareSDK.initSDK(this);
    }
}
