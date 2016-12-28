package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by liukun on 16/3/5.
 */
public class ChargeBackVo implements Serializable{

    /**
     * applyTime : 2016-06-02 11:48:10
     * buyAmount : 466.66
     * dealState : 4
     * ordersNo : X20160602094024309
     * returnsAmount : 466.66
     * returnsFinal : 0
     * returnsNO : TX20160602094024309
     * returnsType : 部分
     * skuName : 小规模纳税人记账（无出口免税）-松山湖
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
