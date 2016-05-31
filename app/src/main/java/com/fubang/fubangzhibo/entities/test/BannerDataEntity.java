package com.fubang.fubangzhibo.entities.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dell on 2016/3/6.
 */
public class BannerDataEntity {
    public List<Banners> getBanners() {
        return banners;
    }

    public void setBanners(List<Banners> banners) {
        this.banners = banners;
    }

    public BannerDataEntity(List<Banners> banners) {

        this.banners = banners;
    }

    @SerializedName("banners")
    private List<Banners> banners;
}
