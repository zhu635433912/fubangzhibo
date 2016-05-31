package com.fubang.fubangzhibo.model.impl;

import com.fubang.fubangzhibo.entities.HomeEntity;
import com.fubang.fubangzhibo.model.BaseModel;
import com.fubang.fubangzhibo.model.HomeModel;
import com.fubang.fubangzhibo.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public class HomeModelImpl extends BaseModel implements HomeModel{
    private static volatile HomeModelImpl instance = null;

    private HomeModelImpl(){

    }

    public static HomeModelImpl getInstance() {
        if (instance == null) {
            synchronized (HomeModelImpl.class) {
                if (instance == null) {
                    instance = new HomeModelImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void getHomeData(Callback<HomeEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        service.getHomeEntity(map).enqueue(callback);
    }
}
