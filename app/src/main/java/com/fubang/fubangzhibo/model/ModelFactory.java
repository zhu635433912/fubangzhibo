package com.fubang.fubangzhibo.model;

import com.fubang.fubangzhibo.model.impl.AnchorModelImpl;
import com.fubang.fubangzhibo.model.impl.GiftTopModelImpl;
import com.fubang.fubangzhibo.model.impl.HomeHeadPicModelImpl;
import com.fubang.fubangzhibo.model.impl.HomeIconModelImpl;
import com.fubang.fubangzhibo.model.impl.HomeModelImpl;
import com.fubang.fubangzhibo.model.impl.RichModelImpl;
import com.fubang.fubangzhibo.model.impl.RoomListModelImpl;

/**
 * model工厂类
 * Created by dell on 2016/4/5.
 */
public class ModelFactory {
    private static volatile ModelFactory instance = null;

    private ModelFactory(){
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            synchronized (ModelFactory.class) {
                if (instance == null) {
                    instance = new ModelFactory();
                }
            }
        }
        return instance;
    }
    public HomeHeadPicModelImpl getHomeHeadPicModelImpl(){
        return HomeHeadPicModelImpl.getInstance();
    }

    public HomeIconModelImpl getHomeIconModelImpl(){
        return HomeIconModelImpl.getInstance();
    }

    public HomeModelImpl getHomeModelImpl(){
        return HomeModelImpl.getInstance();
    }

    public RoomListModelImpl getRoomListModelImpl(){
        return RoomListModelImpl.getInstance();
    }

    public RichModelImpl getRichModelImpl(){return RichModelImpl.getInstance();}

    public GiftTopModelImpl getGiftTopModelImpl(){return GiftTopModelImpl.getInstance();}

    public AnchorModelImpl getAnchorModelImpl(){return AnchorModelImpl.getInstance();}
}
