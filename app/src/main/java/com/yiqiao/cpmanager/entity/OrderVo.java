package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by liukun on 16/3/5.
 */
public class OrderVo implements Serializable{

    /**
     * ascriptionerId : 1523
     * bargaining : 1
     * createTime : 1481682210000
     * createrId : 1523
     * creationTimestamp : 1482220141595
     * customerId : 662214
     * deleteRemark : false
     * deptId : 268
     * finalAmount : 900
     * hadConfirmAmount : 0
     * hadInvoiceAmount : 0
     * hasEvaluate : 0
     * id : 12540
     * invoiceState : 1
     * isElectronicProtocol : false
     * num : 1
     * orderKind : 2
     * orderSource : 2
     * orderSubmitStatus : 1
     * orderType : 0
     * ordersNo : X20161214102329846
     * ordersState : 2
     * orgId : 748
     * payWay : 1
     * productName : SZL测试分类SPU-市辖区null
     * productPrice : 900
     * productUnit : 元
     * shopOrgId : 749
     * skuId : 17231
     * toBePaid : 900
     * toBoInvoiceAmount : 0
     * totalAmount : 900
     */

    private int ascriptionerId;
    private int bargaining;
    private long createTime;
    private int createrId;
    private long creationTimestamp;
    private int customerId;
    private boolean deleteRemark;
    private int deptId;
    private String finalAmount;
    private int hadConfirmAmount;
    private int hadInvoiceAmount;
    private String hasEvaluate;
    private int id;
    private int invoiceState;
    private boolean isElectronicProtocol;
    private int num;
    private String orderKind;
    private int orderSource;
    private int orderSubmitStatus;
    private int orderType;
    private String ordersNo;
    private int ordersState;
    private int orgId;
    private int payWay;
    private String productName;
    private int productPrice;
    private String productUnit;
    private int shopOrgId;
    private int skuId;
    private int toBePaid;
    private int toBoInvoiceAmount;
    private int totalAmount;



    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;


    public int getAscriptionerId() {
        return ascriptionerId;
    }

    public void setAscriptionerId(int ascriptionerId) {
        this.ascriptionerId = ascriptionerId;
    }

    public int getBargaining() {
        return bargaining;
    }

    public void setBargaining(int bargaining) {
        this.bargaining = bargaining;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCreaterId() {
        return createrId;
    }

    public void setCreaterId(int createrId) {
        this.createrId = createrId;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(boolean deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public int getHadConfirmAmount() {
        return hadConfirmAmount;
    }

    public void setHadConfirmAmount(int hadConfirmAmount) {
        this.hadConfirmAmount = hadConfirmAmount;
    }

    public int getHadInvoiceAmount() {
        return hadInvoiceAmount;
    }

    public void setHadInvoiceAmount(int hadInvoiceAmount) {
        this.hadInvoiceAmount = hadInvoiceAmount;
    }

    public String getHasEvaluate() {
        return hasEvaluate;
    }

    public void setHasEvaluate(String hasEvaluate) {
        this.hasEvaluate = hasEvaluate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(int invoiceState) {
        this.invoiceState = invoiceState;
    }

    public boolean isIsElectronicProtocol() {
        return isElectronicProtocol;
    }

    public void setIsElectronicProtocol(boolean isElectronicProtocol) {
        this.isElectronicProtocol = isElectronicProtocol;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrderKind() {
        return orderKind;
    }

    public void setOrderKind(String orderKind) {
        this.orderKind = orderKind;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public int getOrderSubmitStatus() {
        return orderSubmitStatus;
    }

    public void setOrderSubmitStatus(int orderSubmitStatus) {
        this.orderSubmitStatus = orderSubmitStatus;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
    }

    public int getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(int ordersState) {
        this.ordersState = ordersState;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public int getShopOrgId() {
        return shopOrgId;
    }

    public void setShopOrgId(int shopOrgId) {
        this.shopOrgId = shopOrgId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(int toBePaid) {
        this.toBePaid = toBePaid;
    }

    public int getToBoInvoiceAmount() {
        return toBoInvoiceAmount;
    }

    public void setToBoInvoiceAmount(int toBoInvoiceAmount) {
        this.toBoInvoiceAmount = toBoInvoiceAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
