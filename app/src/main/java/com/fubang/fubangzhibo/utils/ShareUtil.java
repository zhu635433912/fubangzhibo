package com.fubang.fubangzhibo.utils;

import android.content.Context;
import android.view.View;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 第三方分享工具类
 * Created by dell on 2016/4/11.
 */
public class ShareUtil {

    private static volatile ShareUtil instance = null;

    private ShareUtil(){
    }
    public static ShareUtil getInstance() {
        if (instance == null) {
            synchronized (ShareUtil.class) {
                if (instance == null) {
                    instance = new ShareUtil();
                }
            }
        }
        return instance;
    }

    public void share(Context context){
        ShareSDK.initSDK(context);
        OnekeyShare onekeyShare = new OnekeyShare();
        //关闭sso授权
        onekeyShare.disableSSOWhenAuthorize();
        onekeyShare.setTitle("分享标题");
        onekeyShare.setText("分享文本...www.baidu.com");
        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

        //网络图片的url：所有平台
        onekeyShare.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul

        // url：仅在微信（包括好友和朋友圈）中使用
        onekeyShare.setUrl("http://www.baidu.com");   //网友点进链接后，可以看到分享的详情

        // Url：仅在QQ空间使用
        onekeyShare.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情

        // 启动分享GUI
        onekeyShare.show(context);
    }
}
