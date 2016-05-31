package com.fubang.fubangzhibo.entities.test;

import com.google.gson.annotations.SerializedName;


/**
 * Created by dell on 2016/3/6.
 */
public class BannerEntity {
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private BannerDataEntity banners;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BannerDataEntity getBanners() {
        return banners;
    }

    public void setBanners(BannerDataEntity banners) {
        this.banners = banners;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
