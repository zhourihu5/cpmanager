package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2017/1/4.
 *
 * {
 “userName”;// 用户名
 ‘userPicUrl”;// 用户头像
 “redPackageToal”;// 红包可用金额
 ”couponTotall”;// 优惠券可用数量
 “messageTotal”;// 未读消息数量
 “orderTotal”;// 订单数量
 “telphone”;// 客服热线
 “refund”;//退款
 }


 */
public class UserIndexInfo implements Serializable{
    private String userName;
    private String userPicUrl;
    private String redPackageToal;
    private String couponTotall;
    private String messageTotal;
    private String orderTotal;
    private String telphone;
    private String refund;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public String getRedPackageToal() {
        return redPackageToal;
    }

    public void setRedPackageToal(String redPackageToal) {
        this.redPackageToal = redPackageToal;
    }

    public String getCouponTotall() {
        return couponTotall;
    }

    public void setCouponTotall(String couponTotall) {
        this.couponTotall = couponTotall;
    }

    public String getMessageTotal() {
        return messageTotal;
    }

    public void setMessageTotal(String messageTotal) {
        this.messageTotal = messageTotal;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }
}
