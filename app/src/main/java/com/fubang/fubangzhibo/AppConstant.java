package com.fubang.fubangzhibo;

import java.io.File;

/**
 * Created by dell on 2016/4/5.
 */
public class AppConstant {
    public static final String HOME_TYPE = "home_type";
    public static final String[] HOME_TYPE_TITLE = {"单身贵族","网络情缘","魅力舞台","明星点歌"};
    public static final String[] MY_TYPE_TITLE = {"关注主播","收藏房间","观看历史"};
    public static final String BASE_URL = "http://115.231.26.180:8786/index.php/app/";
    public static final String VER = "ver";
    public static final String OS = "os";
    public static final String TIME = "time";
    public static final String COUNT = "count";
    public static final String PAGE = "page";
    public static final String GROUP = "group";

    public static final  String IMAGE_CACHE = App_.getInstance().getCacheDir().getAbsolutePath()+"";
    public static File getCacheFile(){
        File file = new File(IMAGE_CACHE);
        return file;
    }
}
