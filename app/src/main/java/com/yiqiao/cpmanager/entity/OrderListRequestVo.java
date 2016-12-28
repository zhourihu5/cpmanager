package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class OrderListRequestVo implements Serializable{
   private String customerId;
   private String orderState;
//   private String commentState;
   private int page;
    private int pageSize;

    public OrderListRequestVo(String customId, String orderState) {
        this.customerId = customId;
        this.orderState = orderState;
//        this.commentState = commentState;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
