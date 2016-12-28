package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/20.
 */

public class OrderDetailVo implements Serializable{


    /**
     * orderInvoiceVoList : []
     * orderPayVoList : []
     * orderVo : {"address":"线下网点","areaCounty":"崇文区","bargaining":1,"cancelReason":"","city":"北京市","comments":"","consignee":"szw1608159814","country":"","createTime":1482375311000,"creationTimestamp":1482375343000,"customerId":662214,"deleteRemark":false,"deptId":266,"discount":760,"email":"","finalAmount":3128,"freight":0,"fullsubAmount":0,"hadConfirmAmount":0,"hadInvoiceAmount":0,"id":13035,"integral":3888,"intergralAmount":0,"invoiceState":1,"isElectronicProtocol":true,"isLocked":0,"isSplit":0,"junction":"","mobilePhone":"13381199816","modificationTimestamp":1482375343000,"num":1,"orderKind":"2","orderPointScount":3888,"orderSource":1,"orderSubmitStatus":1,"orderType":0,"ordersNo":"Z201612221055432517","ordersRemark":"","ordersState":2,"orgId":744,"otherReason":"","payWay":2,"phone":"13381199816","postcode":"","preferential":"满减活动：1000,满100.00减20.00 共38次;满赠活动：满赠活动测试,满200.00赠  共1次;","productName":"ZZ创业类-崇文区","productPrice":3888,"productUnit":"元","redbagAmount":0,"refundAmount":0,"regionLocation":"北京市","remark":"源套餐编号:S20161222105511037 源套餐状态: 2","shareNo":"","shopOrgId":744,"skuId":16978,"storageId":739,"toBePaid":3128,"toBoInvoiceAmount":0,"totalAmount":3888,"totalOrdersNo":"Z201612221055432517","tradeNo":"","transferPhone":""}
     */

    private OrderVoBean orderVo;
    private List<?> orderInvoiceVoList;
    private List<?> orderPayVoList;

    public OrderVoBean getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVoBean orderVo) {
        this.orderVo = orderVo;
    }

    public List<?> getOrderInvoiceVoList() {
        return orderInvoiceVoList;
    }

    public void setOrderInvoiceVoList(List<?> orderInvoiceVoList) {
        this.orderInvoiceVoList = orderInvoiceVoList;
    }

    public List<?> getOrderPayVoList() {
        return orderPayVoList;
    }

    public void setOrderPayVoList(List<?> orderPayVoList) {
        this.orderPayVoList = orderPayVoList;
    }

    public static class OrderVoBean {
        /**
         * address : 线下网点
         * areaCounty : 崇文区
         * bargaining : 1
         * cancelReason :
         * city : 北京市
         * comments :
         * consignee : szw1608159814
         * country :
         * createTime : 1482375311000
         * creationTimestamp : 1482375343000
         * customerId : 662214
         * deleteRemark : false
         * deptId : 266
         * discount : 760
         * email :
         * finalAmount : 3128
         * freight : 0
         * fullsubAmount : 0
         * hadConfirmAmount : 0
         * hadInvoiceAmount : 0
         * id : 13035
         * integral : 3888
         * intergralAmount : 0
         * invoiceState : 1
         * isElectronicProtocol : true
         * isLocked : 0
         * isSplit : 0
         * junction :
         * mobilePhone : 13381199816
         * modificationTimestamp : 1482375343000
         * num : 1
         * orderKind : 2
         * orderPointScount : 3888
         * orderSource : 1
         * orderSubmitStatus : 1
         * orderType : 0
         * ordersNo : Z201612221055432517
         * ordersRemark :
         * ordersState : 2
         * orgId : 744
         * otherReason :
         * payWay : 2
         * phone : 13381199816
         * postcode :
         * preferential : 满减活动：1000,满100.00减20.00 共38次;满赠活动：满赠活动测试,满200.00赠  共1次;
         * productName : ZZ创业类-崇文区
         * productPrice : 3888
         * productUnit : 元
         * redbagAmount : 0
         * refundAmount : 0
         * regionLocation : 北京市
         * remark : 源套餐编号:S20161222105511037 源套餐状态: 2
         * shareNo :
         * shopOrgId : 744
         * skuId : 16978
         * storageId : 739
         * toBePaid : 3128
         * toBoInvoiceAmount : 0
         * totalAmount : 3888
         * totalOrdersNo : Z201612221055432517
         * tradeNo :
         * transferPhone :
         */

        private String address;
        private String areaCounty;
        private int bargaining;
        private String cancelReason;
        private String city;
        private String comments;
        private String consignee;
        private String country;
        private long createTime;
        private long creationTimestamp;
        private int customerId;
        private boolean deleteRemark;
        private int deptId;
        private int discount;
        private String email;
        private int finalAmount;
        private int freight;
        private int fullsubAmount;
        private int hadConfirmAmount;
        private int hadInvoiceAmount;
        private int id;
        private int integral;
        private int intergralAmount;
        private int invoiceState;
        private boolean isElectronicProtocol;
        private int isLocked;
        private int isSplit;
        private String junction;
        private String mobilePhone;
        private long modificationTimestamp;
        private int num;
        private String orderKind;
        private int orderPointScount;
        private int orderSource;
        private int orderSubmitStatus;
        private int orderType;
        private String ordersNo;
        private String ordersRemark;
        private int ordersState;
        private int orgId;
        private String otherReason;
        private int payWay;
        private String phone;
        private String postcode;
        private String preferential;
        private String productName;
        private int productPrice;
        private String productUnit;
        private int redbagAmount;
        private int refundAmount;
        private String regionLocation;
        private String remark;
        private String shareNo;
        private int shopOrgId;
        private int skuId;
        private int storageId;
        private int toBePaid;
        private int toBoInvoiceAmount;
        private int totalAmount;
        private String totalOrdersNo;
        private String tradeNo;
        private String transferPhone;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAreaCounty() {
            return areaCounty;
        }

        public void setAreaCounty(String areaCounty) {
            this.areaCounty = areaCounty;
        }

        public int getBargaining() {
            return bargaining;
        }

        public void setBargaining(int bargaining) {
            this.bargaining = bargaining;
        }

        public String getCancelReason() {
            return cancelReason;
        }

        public void setCancelReason(String cancelReason) {
            this.cancelReason = cancelReason;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(int finalAmount) {
            this.finalAmount = finalAmount;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getFullsubAmount() {
            return fullsubAmount;
        }

        public void setFullsubAmount(int fullsubAmount) {
            this.fullsubAmount = fullsubAmount;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getIntergralAmount() {
            return intergralAmount;
        }

        public void setIntergralAmount(int intergralAmount) {
            this.intergralAmount = intergralAmount;
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

        public int getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(int isLocked) {
            this.isLocked = isLocked;
        }

        public int getIsSplit() {
            return isSplit;
        }

        public void setIsSplit(int isSplit) {
            this.isSplit = isSplit;
        }

        public String getJunction() {
            return junction;
        }

        public void setJunction(String junction) {
            this.junction = junction;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public long getModificationTimestamp() {
            return modificationTimestamp;
        }

        public void setModificationTimestamp(long modificationTimestamp) {
            this.modificationTimestamp = modificationTimestamp;
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

        public int getOrderPointScount() {
            return orderPointScount;
        }

        public void setOrderPointScount(int orderPointScount) {
            this.orderPointScount = orderPointScount;
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

        public String getOrdersRemark() {
            return ordersRemark;
        }

        public void setOrdersRemark(String ordersRemark) {
            this.ordersRemark = ordersRemark;
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

        public String getOtherReason() {
            return otherReason;
        }

        public void setOtherReason(String otherReason) {
            this.otherReason = otherReason;
        }

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getPreferential() {
            return preferential;
        }

        public void setPreferential(String preferential) {
            this.preferential = preferential;
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

        public int getRedbagAmount() {
            return redbagAmount;
        }

        public void setRedbagAmount(int redbagAmount) {
            this.redbagAmount = redbagAmount;
        }

        public int getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(int refundAmount) {
            this.refundAmount = refundAmount;
        }

        public String getRegionLocation() {
            return regionLocation;
        }

        public void setRegionLocation(String regionLocation) {
            this.regionLocation = regionLocation;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getShareNo() {
            return shareNo;
        }

        public void setShareNo(String shareNo) {
            this.shareNo = shareNo;
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

        public int getStorageId() {
            return storageId;
        }

        public void setStorageId(int storageId) {
            this.storageId = storageId;
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

        public String getTotalOrdersNo() {
            return totalOrdersNo;
        }

        public void setTotalOrdersNo(String totalOrdersNo) {
            this.totalOrdersNo = totalOrdersNo;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public String getTransferPhone() {
            return transferPhone;
        }

        public void setTransferPhone(String transferPhone) {
            this.transferPhone = transferPhone;
        }
    }
}
