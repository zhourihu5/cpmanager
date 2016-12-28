package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class LoginRequestVo implements Serializable{
   private String username;
   private String password;

    public LoginRequestVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
