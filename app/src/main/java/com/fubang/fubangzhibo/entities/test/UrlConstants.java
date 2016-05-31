package com.fubang.fubangzhibo.entities.test;

/**
 * Created by dell on 2016/3/5.
 */
public class UrlConstants {
    public static final String BASE_URL1 = "http://api.liwushuo.com/v2/channels/";
    public static final String BASE_URL2 = "items?limit=20&ad=2&gender=1&offset=0&generation=2";
    public static final String BANNERS_FIRST_URL = "http://api.liwushuo.com/v2/";
    public static final String BANNERS_SECOND_URL = "http://api.liwushuo.com/v2/";
    public static final String All_STRATEGY_URL = "http://api.liwushuo.com/v2/channel_groups/all";
    public static final String CATE_TOPIC_URL = "http://api.liwushuo.com/v2/collections?limit=20&offset=0";
    public static String getUrl(int typeId){
        return BASE_URL1+typeId+"/"+BASE_URL2;
    }
}
