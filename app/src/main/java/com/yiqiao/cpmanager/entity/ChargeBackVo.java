package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by liukun on 16/3/5.
 */
public class ChargeBackVo implements Serializable{

    /**

     {
     "applyTime": "2016-04-23 21:30:02",//申请时间
     "buyAmount": "95",//购买金额
     "ordersNo": "X20160423184048555",//订单编号
     "returnsAmount": "0",//退款金额
     "returnsFinal": "95",//实际退款金额
     "returnsNO": "TX20160423184048555",//退款申请号
     "returnsType": "全部",//退款类型  全部 ，部分  （和订单的收款金额比较）
     "skuName": "12个月小规模记账"产品名称
     "deptName":"东莞市松山湖创新科技园店"//门店名称
     "contacts":"蔡经理"//经理名称
     "phone":"13200000051" 电话
     }

     */

    private String applyTime;
    private String buyAmount;
    private String dealState;
    private String ordersNo;
    private String returnsAmount;
    private String returnsFinal;
    private String returnsNO;
    private String returnsType;
    private String skuName;
    private String deptName;
    private String contacts;
    private String phone;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    public String getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
    }

    public String getReturnsAmount() {
        return returnsAmount;
    }

    public void setReturnsAmount(String returnsAmount) {
        this.returnsAmount = returnsAmount;
    }

    public String getReturnsFinal() {
        return returnsFinal;
    }

    public void setReturnsFinal(String returnsFinal) {
        this.returnsFinal = returnsFinal;
    }

    public String getReturnsNO() {
        return returnsNO;
    }

    public void setReturnsNO(String returnsNO) {
        this.returnsNO = returnsNO;
    }

    public String getReturnsType() {
        return returnsType;
    }

    public void setReturnsType(String returnsType) {
        this.returnsType = returnsType;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
}
