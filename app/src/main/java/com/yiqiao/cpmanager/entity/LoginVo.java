package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/15.
 */

public class LoginVo implements Serializable{

    /**
     * phone : 13671049959
     * customerId : 662084
     * username : szw1608023784
     */

    private String phone;
    private int customerId;
    private String username;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "phone='" + phone + '\'' +
                ", customerId=" + customerId +
                ", username='" + username + '\'' +
                '}';
    }
}
