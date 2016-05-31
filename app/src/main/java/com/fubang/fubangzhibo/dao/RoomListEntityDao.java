package com.fubang.fubangzhibo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.fubang.fubangzhibo.entities.RoomListEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ROOM_LIST_ENTITY".
*/
public class RoomListEntityDao extends AbstractDao<RoomListEntity, Long> {

    public static final String TABLENAME = "ROOM_LIST_ENTITY";

    /**
     * Properties of entity RoomListEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Roomid = new Property(1, String.class, "roomid", false, "ROOMID");
        public final static Property Roomname = new Property(2, String.class, "roomname", false, "ROOMNAME");
        public final static Property Roompic = new Property(3, String.class, "roompic", false, "ROOMPIC");
        public final static Property Roomrs = new Property(4, String.class, "roomrs", false, "ROOMRS");
        public final static Property Roompwd = new Property(5, String.class, "roompwd", false, "ROOMPWD");
        public final static Property Rscount = new Property(6, String.class, "rscount", false, "RSCOUNT");
        public final static Property Gateway = new Property(7, String.class, "gateway", false, "GATEWAY");
    };


    public RoomListEntityDao(DaoConfig config) {
        super(config);
    }
    
    public RoomListEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROOM_LIST_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ROOMID\" TEXT," + // 1: roomid
                "\"ROOMNAME\" TEXT," + // 2: roomname
                "\"ROOMPIC\" TEXT," + // 3: roompic
                "\"ROOMRS\" TEXT," + // 4: roomrs
                "\"ROOMPWD\" TEXT," + // 5: roompwd
                "\"RSCOUNT\" TEXT," + // 6: rscount
                "\"GATEWAY\" TEXT);"); // 7: gateway
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROOM_LIST_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RoomListEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String roomid = entity.getRoomid();
        if (roomid != null) {
            stmt.bindString(2, roomid);
        }
 
        String roomname = entity.getRoomname();
        if (roomname != null) {
            stmt.bindString(3, roomname);
        }
 
        String roompic = entity.getRoompic();
        if (roompic != null) {
            stmt.bindString(4, roompic);
        }
 
        String roomrs = entity.getRoomrs();
        if (roomrs != null) {
            stmt.bindString(5, roomrs);
        }
 
        String roompwd = entity.getRoompwd();
        if (roompwd != null) {
            stmt.bindString(6, roompwd);
        }
 
        String rscount = entity.getRscount();
        if (rscount != null) {
            stmt.bindString(7, rscount);
        }
 
        String gateway = entity.getGateway();
        if (gateway != null) {
            stmt.bindString(8, gateway);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RoomListEntity readEntity(Cursor cursor, int offset) {
        RoomListEntity entity = new RoomListEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // roomid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // roomname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // roompic
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // roomrs
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // roompwd
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // rscount
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // gateway
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RoomListEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRoomid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRoomname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRoompic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRoomrs(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRoompwd(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRscount(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGateway(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RoomListEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RoomListEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}