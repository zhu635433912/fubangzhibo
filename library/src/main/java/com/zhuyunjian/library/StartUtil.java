package com.zhuyunjian.library;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 判断是否第一次启动app
 * Created by dell on 2016/3/4.
 */
public class StartUtil {
    public static final String APP_START_KEY = "isFirst";
    public static final String APP_START_FIRST = "manhua_first";
    public static final String APP_THEME_LIGHT = "isLight";
    public static final String APP_LIGHT_KEY = "light_model";
    public static final String DOWN_COUNT = "down_count";
    public static final String DOWN_COUNT_KEY = "down_count_key";
    public static final String USER_INFO = "user_info";
    public static final String USER_ICON = "user_icon";
    public static final String USER_NAME = "user_name";
    public static final String USER_ID = "user_id";
    public static final String USER_PWD = "user_pwd";
    public static boolean isFirst(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_START_FIRST,Context.MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean(APP_START_KEY,true);
        if (isFirst){
            preferences.edit().putBoolean(APP_START_KEY,false).commit();
        }
        return isFirst;
    }
//public static boolean isFirst(Context context){
//        SharedPreferences preferences = context.getSharedPreferences("first",Context.MODE_PRIVATE);
//        boolean isFirst = preferences.getBoolean("isFirst",true);
//        if (isFirst){
//            preferences.edit().putBoolean("isFirst",false);
//        }
//        return isFirst;
//}
    public static boolean isLight(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_THEME_LIGHT,Context.MODE_PRIVATE);
        boolean isLight = preferences.getBoolean(APP_LIGHT_KEY,true);
        return isLight;
    }

    public static void editLight(Context context,boolean isNight){
        SharedPreferences preferences = context.getSharedPreferences(APP_THEME_LIGHT,Context.MODE_PRIVATE);
        preferences.edit().putBoolean(APP_LIGHT_KEY,isNight).commit();
    }

    public static int getCount(Context context){
        SharedPreferences preferences = context.getSharedPreferences(DOWN_COUNT,Context.MODE_PRIVATE);
        int count = preferences.getInt(DOWN_COUNT_KEY,20);
        return count;
    }

    public static void putCount(Context context,int count){
        SharedPreferences preferences = context.getSharedPreferences(DOWN_COUNT,Context.MODE_PRIVATE);
        preferences.edit().putInt(DOWN_COUNT_KEY,count).commit();
    }
    public static void editInfo(Context context,String userName,String userId,String userIcon,String userPwd){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        preferences.edit().putString(USER_NAME,userName).putString(USER_ICON,userIcon).putString(USER_ID,userId).putString(USER_PWD,userPwd).commit();
    }
    public static String getUserIcon(Context context){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        String userIcon = preferences.getString(USER_ICON,"");
        return userIcon;
    }
    public static String getUserName(Context context){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        String userIcon = preferences.getString(USER_NAME,"");
        return userIcon;
    }
    public static String getUserPwd(Context context){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        String userPwd = preferences.getString(USER_PWD,"");
        return userPwd;
    }
    public static String getUserId(Context context){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        String userIcon = preferences.getString(USER_ID,"");
        return userIcon;
    }
    public static void deleteLogin(Context context){
        SharedPreferences preferences = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }
}
