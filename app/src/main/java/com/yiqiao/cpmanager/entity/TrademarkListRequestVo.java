package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class TrademarkListRequestVo implements Serializable{

    private String entName;
    private int page;
    private int pageSize;

    public TrademarkListRequestVo(String entName) {
        this.entName = entName;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
