package com.fubang.fubangzhibo.utils;

import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.GiftEntity;

import java.util.ArrayList;
import java.util.List;

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
 * 项目名称：fubangzhibo
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-05-25 14:22
 * 修改人：dell
 * 修改时间：2016-05-25 14:22
 * 修改备注：
 */
public class GiftUtil {

    public static List<GiftEntity> getGifts(){
        List<GiftEntity> list = new ArrayList<>();
        list.add(new GiftEntity(1, R.mipmap.gift1));
        list.add(new GiftEntity(2, R.mipmap.gift2));
        list.add(new GiftEntity(3, R.mipmap.gift3));
        list.add(new GiftEntity(4, R.mipmap.gift4));
        list.add(new GiftEntity(5, R.mipmap.gift5));
        list.add(new GiftEntity(6, R.mipmap.gift6));
        list.add(new GiftEntity(7, R.mipmap.gift7));
        list.add(new GiftEntity(8, R.mipmap.gift8));
        list.add(new GiftEntity(9, R.mipmap.gift9));
        list.add(new GiftEntity(10, R.mipmap.gift10));
        list.add(new GiftEntity(11, R.mipmap.gift11));
        list.add(new GiftEntity(12, R.mipmap.gift12));
        list.add(new GiftEntity(13, R.mipmap.gift13));
        list.add(new GiftEntity(14, R.mipmap.gift14));
        list.add(new GiftEntity(16, R.mipmap.gift16));
        list.add(new GiftEntity(17, R.mipmap.gift17));
        list.add(new GiftEntity(18, R.mipmap.gift18));

        return list;
    }
}
