package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class ChargebackListRequestVo implements Serializable{

    private String customId;
    private int page;
    private int pageSize;

    public ChargebackListRequestVo(String customerId) {
        this.customId = customerId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
