package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2017/1/11.
 *   "data": "orderCreateStatus :1,orderCd:S20170116134632861,orderId:15037",

 */

public class CreateOrderVo implements Serializable{
     private String orderCreateStatus;
     private String orderCd;
     private String orderId;

    public String getOrderCreateStatus() {
        return orderCreateStatus;
    }

    public void setOrderCreateStatus(String orderCreateStatus) {
        this.orderCreateStatus = orderCreateStatus;
    }

    public String getOrderCd() {
        return orderCd;
    }

    public void setOrderCd(String orderCd) {
        this.orderCd = orderCd;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
