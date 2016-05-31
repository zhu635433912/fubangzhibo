package com.fubang.fubangzhibo.model.impl;

import com.fubang.fubangzhibo.entities.HomeHeadPicEntity;
import com.fubang.fubangzhibo.model.BaseModel;
import com.fubang.fubangzhibo.model.HomeHeadPicModel;
import com.fubang.fubangzhibo.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public class HomeHeadPicModelImpl extends BaseModel implements HomeHeadPicModel{
    private static volatile HomeHeadPicModelImpl instance = null;

    private HomeHeadPicModelImpl(){

    }

    public static HomeHeadPicModelImpl getInstance() {
        if (instance == null) {
            synchronized (HomeHeadPicModelImpl.class) {
                if (instance == null) {
                    instance = new HomeHeadPicModelImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public void getHeadPicData(Callback<HomeHeadPicEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        service.getHomeHeadPicEntity(map).enqueue(callback);
    }
}
