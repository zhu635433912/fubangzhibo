package com.fubang.fubangzhibo.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.fubang.fubangzhibo.entities.RoomListEntity;

import com.fubang.fubangzhibo.dao.RoomListEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig roomListEntityDaoConfig;

    private final RoomListEntityDao roomListEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        roomListEntityDaoConfig = daoConfigMap.get(RoomListEntityDao.class).clone();
        roomListEntityDaoConfig.initIdentityScope(type);

        roomListEntityDao = new RoomListEntityDao(roomListEntityDaoConfig, this);

        registerDao(RoomListEntity.class, roomListEntityDao);
    }
    
    public void clear() {
        roomListEntityDaoConfig.getIdentityScope().clear();
    }

    public RoomListEntityDao getRoomListEntityDao() {
        return roomListEntityDao;
    }

}
