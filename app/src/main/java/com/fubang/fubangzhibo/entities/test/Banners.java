package com.fubang.fubangzhibo.entities.test;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 2016/3/6.
 */
public class Banners {
    @SerializedName("id")
    private int id;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("target_url")
    private String target_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public Banners(int id, String image_url, String target_url) {

        this.id = id;
        this.image_url = image_url;
        this.target_url = target_url;
    }
}
