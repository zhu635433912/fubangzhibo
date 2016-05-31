package com.fubang.fubangzhibo.entities.test;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dell on 2016/3/6.
 */
public interface BannerService {
    @GET("banners")
    Call<BannerEntity> getBannerList();
}
