package com.fubang.fubangzhibo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fubang.fubangzhibo.dao.DaoMaster;
import com.fubang.fubangzhibo.dao.DaoSession;
import com.fubang.fubangzhibo.dao.RoomListEntityDao;
import com.fubang.fubangzhibo.entities.RoomListEntity;

/**
 * 数据库工具类
 * Created by dell on 2016/4/7.
 */
public class DbUtil {
    private static DaoSession session;
    public static void init(Context context){
        RoomOpenHelper helper = new RoomOpenHelper(context,"roomlist.db",null);
        session = new DaoMaster(helper.getWritableDatabase()).newSession();
    }
    public static DaoSession getSession(){
        return session;
    }
    private static class RoomOpenHelper extends DaoMaster.DevOpenHelper{

        public RoomOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            super.onCreate(db);
            RoomListEntityDao dao = new DaoMaster(db).newSession().getRoomListEntityDao();
        }
    }
}
