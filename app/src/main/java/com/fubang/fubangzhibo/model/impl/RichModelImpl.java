package com.fubang.fubangzhibo.model.impl;

import com.fubang.fubangzhibo.entities.RichListEntity;
import com.fubang.fubangzhibo.model.BaseModel;
import com.fubang.fubangzhibo.model.RichModel;
import com.fubang.fubangzhibo.utils.ParamsMap;

import retrofit2.Callback;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p/>
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-06-24 17:03
 * 修改人：dell
 * 修改时间：2016-06-24 17:03
 * 修改备注：
 */
public class RichModelImpl extends BaseModel implements RichModel{
    private static volatile RichModelImpl instance = null;

    private RichModelImpl(){

    }

    public static RichModelImpl getInstance() {
        if (instance == null) {
            synchronized (RichModelImpl.class) {
                if (instance == null) {
                    instance = new RichModelImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void getRichEntityData(Callback<RichListEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        map.put("type",1);
        service.getRichEntity(map).enqueue(callback);
    }
}
