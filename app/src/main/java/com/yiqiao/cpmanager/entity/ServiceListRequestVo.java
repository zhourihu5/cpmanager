package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class ServiceListRequestVo implements Serializable{

    private String customerId;
    private int currentPage;
    private int pageSize;
    private String version="1";

    public ServiceListRequestVo(String customerId) {
        this.customerId = customerId;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
