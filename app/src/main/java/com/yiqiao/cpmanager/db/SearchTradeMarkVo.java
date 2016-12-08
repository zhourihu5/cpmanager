package com.yiqiao.cpmanager.db;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by liukun on 16/3/5.
 */
public class SearchTradeMarkVo extends RealmObject implements Serializable{

    private String name;
    private long time;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
