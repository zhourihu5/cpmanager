package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liukun on 16/3/5.
 */
public class OrderVo implements Serializable{

    /**
     * customerId : 3876251
     * orderId : 14057
     * orderList : [{"channel":"线下","createTime":1484192736000,"creationTimestamp":1484201050987,"customerId":3876251,"customerName":"szw1606285701","customerPhone":"15639263516","deleteRemark":false,"deptAddress":"暂无信息","deptManager":"暂无信息","deptName":"暂无信息","deptPhone":"暂无信息","discount":0.01,"electronicProtocol":true,"finalAmount":0.01,"hadConfirmAmount":0,"hadInvoiceAmount":0,"hasEvaluate":"0","id":14057,"invoiceState":1,"isElectronicProtocol":true,"num":1,"orderKind":"2","orderSource":1,"orderType":0,"ordersNo":"S20170112114536304","ordersState":2,"payAgree":1,"productName":"派单","productPrice":0.01,"productType":"0","serviceList":[],"skuId":25197,"snapshot":{"redbag":"0","productActiPic":"1234.jpg","productAmount":0.01,"areaLevelOne":"北京市","coupon":"0","integral":"0","areaLevelThree":"海淀区","areaLevelTwo":"北京市市辖区","attrName":"全国","productName":"派单","productNo":25197},"snapshotData":{"redbag":"0","productActiPic":"1234.jpg","productAmount":0.01,"areaLevelOne":"北京市","coupon":"0","integral":"0","areaLevelThree":"海淀区","areaLevelTwo":"北京市市辖区","attrName":"全国","productName":"派单","productNo":25197},"toBePaid":0.01,"toBoInvoiceAmount":0,"totalAmount":0.01}]
     * ordersNo : S20170112114536304
     */

    private String customerId;
    private String orderId;
    private String ordersNo;
    private List<OrderListBean> orderList;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean implements Serializable{
        /**
         * channel : 线下
         * createTime : 1484192736000
         * creationTimestamp : 1484201050987
         * customerId : 3876251
         * customerName : szw1606285701
         * customerPhone : 15639263516
         * deleteRemark : false
         * deptAddress : 暂无信息
         * deptManager : 暂无信息
         * deptName : 暂无信息
         * deptPhone : 暂无信息
         * discount : 0.01
         * electronicProtocol : true
         * finalAmount : 0.01
         * hadConfirmAmount : 0
         * hadInvoiceAmount : 0
         * hasEvaluate : 0
         * id : 14057
         * invoiceState : 1
         * isElectronicProtocol : true
         * num : 1
         * orderKind : 2
         * orderSource : 1
         * orderType : 0
         * ordersNo : S20170112114536304
         * ordersState : 2
         * payAgree : 1
         * productName : 派单
         * productPrice : 0.01
         * productType : 0
         * serviceList : []
         * skuId : 25197
         * snapshot : {"redbag":"0","productActiPic":"1234.jpg","productAmount":0.01,"areaLevelOne":"北京市","coupon":"0","integral":"0","areaLevelThree":"海淀区","areaLevelTwo":"北京市市辖区","attrName":"全国","productName":"派单","productNo":25197}
         * snapshotData : {"redbag":"0","productActiPic":"1234.jpg","productAmount":0.01,"areaLevelOne":"北京市","coupon":"0","integral":"0","areaLevelThree":"海淀区","areaLevelTwo":"北京市市辖区","attrName":"全国","productName":"派单","productNo":25197}
         * toBePaid : 0.01
         * toBoInvoiceAmount : 0
         * totalAmount : 0.01
         */

        private String channel;
        private long createTime;
        private long creationTimestamp;
        private String customerId;
        private String customerName;
        private String customerPhone;
        private String deleteRemark;
        private String deptAddress;
        private String deptManager;
        private String deptName;
        private String deptPhone;
        private String discount;
        private String electronicProtocol;
        private String finalAmount;
        private String hadConfirmAmount;
        private String hadInvoiceAmount;
        private String hasEvaluate;
        private String id;
        private String invoiceState;
        private String isElectronicProtocol;
        private String num;
        private String orderKind;
        private String orderSource;
        private String orderType;
        private String ordersNo;
        private String ordersState;
        private String payWay;
        private String payAgree;
        private String productName;
        private String productPrice;
        private String productUnit;
        private String productType;
        private String skuId;
        private SnapshotBean snapshot;
        private SnapshotDataBean snapshotData;
        private String toBePaid;
        private String toBoInvoiceAmount;
        private String totalAmount;
        private String couponAmount;
        private String redbagAmount;
        private String intergral;
        private String activityAmount;

        public String getPreferentialAmount() {
            return preferentialAmount;
        }

        public void setPreferentialAmount(String preferentialAmount) {
            this.preferentialAmount = preferentialAmount;
        }

        private String preferentialAmount ;
        private List<ServiceListBean> serviceList;
//        "couponAmount":" 500" //订单优惠券金额
//                "redbagAmount":" 100" //订单红包金额
//                "intergral ":" 20000" //订单积分
//                "activityAmount":" 200" //订单满减金额


        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getRedbagAmount() {
            return redbagAmount;
        }

        public void setRedbagAmount(String redbagAmount) {
            this.redbagAmount = redbagAmount;
        }

        public String getIntergral() {
            return intergral;
        }

        public void setIntergral(String intergral) {
            this.intergral = intergral;
        }

        public String getActivityAmount() {
            return activityAmount;
        }

        public void setActivityAmount(String activityAmount) {
            this.activityAmount = activityAmount;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getProductUnit() {
            return productUnit;
        }

        public void setProductUnit(String productUnit) {
            this.productUnit = productUnit;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String isDeleteRemark() {
            return deleteRemark;
        }

        public void setDeleteRemark(String deleteRemark) {
            this.deleteRemark = deleteRemark;
        }

        public String getDeptAddress() {
            return deptAddress;
        }

        public void setDeptAddress(String deptAddress) {
            this.deptAddress = deptAddress;
        }

        public String getDeptManager() {
            return deptManager;
        }

        public void setDeptManager(String deptManager) {
            this.deptManager = deptManager;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDeptPhone() {
            return deptPhone;
        }

        public void setDeptPhone(String deptPhone) {
            this.deptPhone = deptPhone;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String isElectronicProtocol() {
            return electronicProtocol;
        }

        public void setElectronicProtocol(String electronicProtocol) {
            this.electronicProtocol = electronicProtocol;
        }

        public String getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(String finalAmount) {
            this.finalAmount = finalAmount;
        }

        public String getHadConfirmAmount() {
            return hadConfirmAmount;
        }

        public void setHadConfirmAmount(String hadConfirmAmount) {
            this.hadConfirmAmount = hadConfirmAmount;
        }

        public String getHadInvoiceAmount() {
            return hadInvoiceAmount;
        }

        public void setHadInvoiceAmount(String hadInvoiceAmount) {
            this.hadInvoiceAmount = hadInvoiceAmount;
        }

        public String getHasEvaluate() {
            return hasEvaluate;
        }

        public void setHasEvaluate(String hasEvaluate) {
            this.hasEvaluate = hasEvaluate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvoiceState() {
            return invoiceState;
        }

        public void setInvoiceState(String invoiceState) {
            this.invoiceState = invoiceState;
        }

        public String isIsElectronicProtocol() {
            return isElectronicProtocol;
        }

        public void setIsElectronicProtocol(String isElectronicProtocol) {
            this.isElectronicProtocol = isElectronicProtocol;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrderKind() {
            return orderKind;
        }

        public void setOrderKind(String orderKind) {
            this.orderKind = orderKind;
        }

        public String getOrderSource() {
            return orderSource;
        }

        public void setOrderSource(String orderSource) {
            this.orderSource = orderSource;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrdersNo() {
            return ordersNo;
        }

        public void setOrdersNo(String ordersNo) {
            this.ordersNo = ordersNo;
        }

        public String getOrdersState() {
            return ordersState;
        }

        public void setOrdersState(String ordersState) {
            this.ordersState = ordersState;
        }

        public String getPayAgree() {
            return payAgree;
        }

        public void setPayAgree(String payAgree) {
            this.payAgree = payAgree;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public SnapshotBean getSnapshot() {
            return snapshot;
        }

        public void setSnapshot(SnapshotBean snapshot) {
            this.snapshot = snapshot;
        }

        public SnapshotDataBean getSnapshotData() {
            return snapshotData;
        }

        public void setSnapshotData(SnapshotDataBean snapshotData) {
            this.snapshotData = snapshotData;
        }

        public String getToBePaid() {
            return toBePaid;
        }

        public void setToBePaid(String toBePaid) {
            this.toBePaid = toBePaid;
        }

        public String getToBoInvoiceAmount() {
            return toBoInvoiceAmount;
        }

        public void setToBoInvoiceAmount(String toBoInvoiceAmount) {
            this.toBoInvoiceAmount = toBoInvoiceAmount;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<ServiceListBean> getServiceList() {
            return serviceList;
        }

        public void setServiceList(List<ServiceListBean> serviceList) {
            this.serviceList = serviceList;
        }

        public static class SnapshotBean implements Serializable{
            /**
             * redbag : 0
             * productActiPic : 1234.jpg
             * productAmount : 0.01
             * areaLevelOne : 北京市
             * coupon : 0
             * integral : 0
             * areaLevelThree : 海淀区
             * areaLevelTwo : 北京市市辖区
             * attrName : 全国
             * productName : 派单
             * productNo : 25197
             */

            private String redbag;
            private String productActiPic;
            private String productAmount;
            private String areaLevelOne;
            private String coupon;
            private String integral;
            private String areaLevelThree;
            private String areaLevelTwo;
            private String attrName;
            private String productName;
            private String productNo;

            public String getRedbag() {
                return redbag;
            }

            public void setRedbag(String redbag) {
                this.redbag = redbag;
            }

            public String getProductActiPic() {
                return productActiPic;
            }

            public void setProductActiPic(String productActiPic) {
                this.productActiPic = productActiPic;
            }

            public String getProductAmount() {
                return productAmount;
            }

            public void setProductAmount(String productAmount) {
                this.productAmount = productAmount;
            }

            public String getAreaLevelOne() {
                return areaLevelOne;
            }

            public void setAreaLevelOne(String areaLevelOne) {
                this.areaLevelOne = areaLevelOne;
            }

            public String getCoupon() {
                return coupon;
            }

            public void setCoupon(String coupon) {
                this.coupon = coupon;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public String getAreaLevelThree() {
                return areaLevelThree;
            }

            public void setAreaLevelThree(String areaLevelThree) {
                this.areaLevelThree = areaLevelThree;
            }

            public String getAreaLevelTwo() {
                return areaLevelTwo;
            }

            public void setAreaLevelTwo(String areaLevelTwo) {
                this.areaLevelTwo = areaLevelTwo;
            }

            public String getAttrName() {
                return attrName;
            }

            public void setAttrName(String attrName) {
                this.attrName = attrName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductNo() {
                return productNo;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }
        }

        public static class SnapshotDataBean implements Serializable{
            /**
             * redbag : 0
             * productActiPic : 1234.jpg
             * productAmount : 0.01
             * areaLevelOne : 北京市
             * coupon : 0
             * integral : 0
             * areaLevelThree : 海淀区
             * areaLevelTwo : 北京市市辖区
             * attrName : 全国
             * productName : 派单
             * productNo : 25197
             */

            private String redbag;
            private String productActiPic;
            private String productAmount;
            private String areaLevelOne;
            private String coupon;
            private String integral;
            private String areaLevelThree;
            private String areaLevelTwo;
            private String attrName;
            private String productName;
            private String productNo;

            public String getRedbag() {
                return redbag;
            }

            public void setRedbag(String redbag) {
                this.redbag = redbag;
            }

            public String getProductActiPic() {
                return productActiPic;
            }

            public void setProductActiPic(String productActiPic) {
                this.productActiPic = productActiPic;
            }

            public String getProductAmount() {
                return productAmount;
            }

            public void setProductAmount(String productAmount) {
                this.productAmount = productAmount;
            }

            public String getAreaLevelOne() {
                return areaLevelOne;
            }

            public void setAreaLevelOne(String areaLevelOne) {
                this.areaLevelOne = areaLevelOne;
            }

            public String getCoupon() {
                return coupon;
            }

            public void setCoupon(String coupon) {
                this.coupon = coupon;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public String getAreaLevelThree() {
                return areaLevelThree;
            }

            public void setAreaLevelThree(String areaLevelThree) {
                this.areaLevelThree = areaLevelThree;
            }

            public String getAreaLevelTwo() {
                return areaLevelTwo;
            }

            public void setAreaLevelTwo(String areaLevelTwo) {
                this.areaLevelTwo = areaLevelTwo;
            }

            public String getAttrName() {
                return attrName;
            }

            public void setAttrName(String attrName) {
                this.attrName = attrName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductNo() {
                return productNo;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }
        }
        public static class ServiceListBean implements Serializable{
            /**
             {
             " goodsSku ":"1122" ,// 服务产品SKU
             " goodsName ":"法人变更（次）" ,// 服务产品名称
             " goodsAmount ":" 698.00 " ,// 服务产品金额
             }
             */

            private String goodsSku;
            private String goodsName;
            private String goodsAmount;

            public String getGoodsSku() {
                return goodsSku;
            }

            public void setGoodsSku(String goodsSku) {
                this.goodsSku = goodsSku;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsAmount() {
                return goodsAmount;
            }

            public void setGoodsAmount(String goodsAmount) {
                this.goodsAmount = goodsAmount;
            }
        }

    }
}
