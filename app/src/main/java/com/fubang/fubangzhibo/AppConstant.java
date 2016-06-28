package com.fubang.fubangzhibo;

import java.io.File;

import retrofit2.http.PUT;

/**
 * 常量工具类
 * Created by dell on 2016/4/5.
 */
public class AppConstant {
    public static final String HOME_TYPE = "home_type";
    public static final String[] HOME_TYPE_TITLE = {"单身贵族","网络情缘","魅力舞台","明星点歌"};
    public static final String[] MY_TYPE_TITLE = {"关注主播","收藏房间","观看历史"};
    public static final String[] BILLBOARD_TITLE = {"富豪","礼物","主播","房间"};
    public static final String BASE_URL = "http://121.41.112.232:9418/index.php/";
    public static final String VER = "ver";
    public static final String OS = "os";
    public static final String TIME = "time";
    public static final String COUNT = "count";
    public static final String PAGE = "page";
    public static final String GROUP = "group";

    //设置图片缓存地址
    public static final  String IMAGE_CACHE = App_.getInstance().getCacheDir().getAbsolutePath()+"";
    public static File getCacheFile(){
        File file = new File(IMAGE_CACHE);
        return file;
    }
}
