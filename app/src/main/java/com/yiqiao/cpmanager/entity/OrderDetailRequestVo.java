package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class OrderDetailRequestVo implements Serializable{

    private String orderId;

    public OrderDetailRequestVo(String id) {
        this.orderId = id;
    }
}
