package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class RoomListEntity {
    public static void main(String[] args) {
//        roomid: "80000",
//        roomname: "80000",
//        roompic: "",
//        roomrs: "300",
//        roompwd: "",
//        rscount: "201",
//        gateway:""
        Schema schema = new Schema(1,"com.fubang.fubangzhibo.entities");
        schema.setDefaultJavaPackageDao("com.fubang.fubangzhibo.dao");
        Entity roomListEntity = schema.addEntity("RoomListEntity");
        roomListEntity.addIdProperty();
        roomListEntity.addStringProperty("roomid");
        roomListEntity.addStringProperty("roomname");
        roomListEntity.addStringProperty("roompic");
        roomListEntity.addStringProperty("roomrs");
        roomListEntity.addStringProperty("roompwd");
        roomListEntity.addStringProperty("rscount");
        roomListEntity.addStringProperty("gateway");
        try {
            DaoGenerator generator = new DaoGenerator();
            generator.generateAll(schema,"app/src/main/java");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
