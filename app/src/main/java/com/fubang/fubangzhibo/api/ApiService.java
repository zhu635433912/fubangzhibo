package com.fubang.fubangzhibo.api;

import com.fubang.fubangzhibo.entities.HomeEntity;
import com.fubang.fubangzhibo.entities.HomeHeadPicEntity;
import com.fubang.fubangzhibo.entities.HomeIconEntity;
import com.fubang.fubangzhibo.entities.RoomEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by dell on 2016/4/5.
 */
public interface ApiService {
    @POST("touch/apps.banner?")
    Call<HomeHeadPicEntity> getHomeHeadPicEntity(@QueryMap Map<String,String> map);

    @POST("static/apps.recommend/game.json?")
    Call<HomeIconEntity> getHomeIconEntity(@QueryMap Map<String,String> map);

    @POST("static/live.index/recommend-apps.json?")
    Call<HomeEntity> getHomeEntity(@QueryMap Map<String,String> map);

    @GET("roomlist?")
    Call<RoomEntity> getRoomEntity(@QueryMap Map<String,String> map);
}
