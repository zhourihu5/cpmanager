package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/20.
 */

public class OrderDetailVo implements Serializable{
    /**
     * all_List : {"customerId":3894484,"orderId":15113,"orderList":[{"bargaining":0,"channel":"线下","createTime":1484553325000,"creationTimestamp":1484553707242,"customerId":3894484,"customerName":"","customerPhone":"13671049959","deleteRemark":false,"deptAddress":"暂无信息","deptManager":"暂无信息","deptName":"暂无信息","deptPhone":"暂无信息","discount":111,"electronicProtocol":true,"finalAmount":111,"hadConfirmAmount":0,"hadInvoiceAmount":0,"hasEvaluate":"0","id":15113,"invoiceState":1,"isElectronicProtocol":true,"num":1,"orderKind":"2","orderSource":1,"orderType":0,"ordersNo":"S20170116155524969","ordersState":2,"payAgree":1,"productName":"文印店定点商品（无需跑腿）","productPrice":111,"productType":"0","productUnit":"","serviceList":[],"skuId":25323,"snapshot":{"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323},"snapshotData":{"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323},"toBePaid":111,"toBoInvoiceAmount":0,"totalAmount":111}],"ordersNo":"S20170116155524969"}
     */

    private AllListBean all_List;

    public AllListBean getAll_List() {
        return all_List;
    }

    public void setAll_List(AllListBean all_List) {
        this.all_List = all_List;
    }

    public static class AllListBean {
        /**
         * customerId : 3894484
         * orderId : 15113
         * orderList : [{"bargaining":0,"channel":"线下","createTime":1484553325000,"creationTimestamp":1484553707242,"customerId":3894484,"customerName":"","customerPhone":"13671049959","deleteRemark":false,"deptAddress":"暂无信息","deptManager":"暂无信息","deptName":"暂无信息","deptPhone":"暂无信息","discount":111,"electronicProtocol":true,"finalAmount":111,"hadConfirmAmount":0,"hadInvoiceAmount":0,"hasEvaluate":"0","id":15113,"invoiceState":1,"isElectronicProtocol":true,"num":1,"orderKind":"2","orderSource":1,"orderType":0,"ordersNo":"S20170116155524969","ordersState":2,"payAgree":1,"productName":"文印店定点商品（无需跑腿）","productPrice":111,"productType":"0","productUnit":"","serviceList":[],"skuId":25323,"snapshot":{"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323},"snapshotData":{"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323},"toBePaid":111,"toBoInvoiceAmount":0,"totalAmount":111}]
         * ordersNo : S20170116155524969
         */

        private int customerId;
        private int orderId;
        private String ordersNo;
        private List<OrderListBean> orderList;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
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

        public static class OrderListBean {
            /**
             * bargaining : 0
             * channel : 线下
             * createTime : 1484553325000
             * creationTimestamp : 1484553707242
             * customerId : 3894484
             * customerName :
             * customerPhone : 13671049959
             * deleteRemark : false
             * deptAddress : 暂无信息
             * deptManager : 暂无信息
             * deptName : 暂无信息
             * deptPhone : 暂无信息
             * discount : 111
             * electronicProtocol : true
             * finalAmount : 111
             * hadConfirmAmount : 0
             * hadInvoiceAmount : 0
             * hasEvaluate : 0
             * id : 15113
             * invoiceState : 1
             * isElectronicProtocol : true
             * num : 1
             * orderKind : 2
             * orderSource : 1
             * orderType : 0
             * ordersNo : S20170116155524969
             * ordersState : 2
             * payAgree : 1
             * productName : 文印店定点商品（无需跑腿）
             * productPrice : 111
             * productType : 0
             * productUnit :
             * serviceList : []
             * skuId : 25323
             * snapshot : {"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323}
             * snapshotData : {"redbag":"10.0","productActiPic":"1234.jpg","productAmount":111,"areaLevelOne":"湖北省","coupon":"0","integral":"55","areaLevelThree":"武穴市","areaLevelTwo":"黄冈市","attrName":"","productName":"文印店定点商品（无需跑腿）","productNo":25323}
             * toBePaid : 111
             * toBoInvoiceAmount : 0
             * totalAmount : 111
             */

            private int bargaining;
            private String channel;
            private long createTime;
            private long creationTimestamp;
            private int customerId;
            private String customerName;
            private String customerPhone;
            private boolean deleteRemark;
            private String deptAddress;
            private String deptManager;
            private String deptName;
            private String deptPhone;
            private int discount;
            private boolean electronicProtocol;
            private int finalAmount;
            private int hadConfirmAmount;
            private int hadInvoiceAmount;
            private String hasEvaluate;
            private int id;
            private int invoiceState;
            private boolean isElectronicProtocol;
            private int num;
            private String orderKind;
            private int orderSource;
            private int orderType;
            private String ordersNo;
            private int ordersState;
            private int payAgree;
            private String productName;
            private int productPrice;
            private String productType;
            private String productUnit;
            private int skuId;
            private SnapshotBean snapshot;
            private SnapshotDataBean snapshotData;
            private int toBePaid;
            private int toBoInvoiceAmount;
            private int totalAmount;
            private List<?> serviceList;

            public int getBargaining() {
                return bargaining;
            }

            public void setBargaining(int bargaining) {
                this.bargaining = bargaining;
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

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
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

            public boolean isDeleteRemark() {
                return deleteRemark;
            }

            public void setDeleteRemark(boolean deleteRemark) {
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

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public boolean isElectronicProtocol() {
                return electronicProtocol;
            }

            public void setElectronicProtocol(boolean electronicProtocol) {
                this.electronicProtocol = electronicProtocol;
            }

            public int getFinalAmount() {
                return finalAmount;
            }

            public void setFinalAmount(int finalAmount) {
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

            public int getPayAgree() {
                return payAgree;
            }

            public void setPayAgree(int payAgree) {
                this.payAgree = payAgree;
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

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getProductUnit() {
                return productUnit;
            }

            public void setProductUnit(String productUnit) {
                this.productUnit = productUnit;
            }

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
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

            public List<?> getServiceList() {
                return serviceList;
            }

            public void setServiceList(List<?> serviceList) {
                this.serviceList = serviceList;
            }

            public static class SnapshotBean {
                /**
                 * redbag : 10.0
                 * productActiPic : 1234.jpg
                 * productAmount : 111
                 * areaLevelOne : 湖北省
                 * coupon : 0
                 * integral : 55
                 * areaLevelThree : 武穴市
                 * areaLevelTwo : 黄冈市
                 * attrName :
                 * productName : 文印店定点商品（无需跑腿）
                 * productNo : 25323
                 */

                private String redbag;
                private String productActiPic;
                private int productAmount;
                private String areaLevelOne;
                private String coupon;
                private String integral;
                private String areaLevelThree;
                private String areaLevelTwo;
                private String attrName;
                private String productName;
                private int productNo;

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

                public int getProductAmount() {
                    return productAmount;
                }

                public void setProductAmount(int productAmount) {
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

                public int getProductNo() {
                    return productNo;
                }

                public void setProductNo(int productNo) {
                    this.productNo = productNo;
                }
            }

            public static class SnapshotDataBean {
                /**
                 * redbag : 10.0
                 * productActiPic : 1234.jpg
                 * productAmount : 111
                 * areaLevelOne : 湖北省
                 * coupon : 0
                 * integral : 55
                 * areaLevelThree : 武穴市
                 * areaLevelTwo : 黄冈市
                 * attrName :
                 * productName : 文印店定点商品（无需跑腿）
                 * productNo : 25323
                 */

                private String redbag;
                private String productActiPic;
                private int productAmount;
                private String areaLevelOne;
                private String coupon;
                private String integral;
                private String areaLevelThree;
                private String areaLevelTwo;
                private String attrName;
                private String productName;
                private int productNo;

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

                public int getProductAmount() {
                    return productAmount;
                }

                public void setProductAmount(int productAmount) {
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

                public int getProductNo() {
                    return productNo;
                }

                public void setProductNo(int productNo) {
                    this.productNo = productNo;
                }
            }
        }
    }



/*
    *//**
     * orderInvoiceVoList : []
     * orderPayVoList : []
     * orderVo : {"address":"线下网点","areaCounty":"崇文区","bargaining":1,"cancelReason":"","city":"北京市","comments":"","consignee":"szw1608159814","country":"","createTime":1482375311000,"creationTimestamp":1482375343000,"customerId":662214,"deleteRemark":false,"deptId":266,"discount":760,"email":"","finalAmount":3128,"freight":0,"fullsubAmount":0,"hadConfirmAmount":0,"hadInvoiceAmount":0,"id":13035,"integral":3888,"intergralAmount":0,"invoiceState":1,"isElectronicProtocol":true,"isLocked":0,"isSplit":0,"junction":"","mobilePhone":"13381199816","modificationTimestamp":1482375343000,"num":1,"orderKind":"2","orderPointScount":3888,"orderSource":1,"orderSubmitStatus":1,"orderType":0,"ordersNo":"Z201612221055432517","ordersRemark":"","ordersState":2,"orgId":744,"otherReason":"","payWay":2,"phone":"13381199816","postcode":"","preferential":"满减活动：1000,满100.00减20.00 共38次;满赠活动：满赠活动测试,满200.00赠  共1次;","productName":"ZZ创业类-崇文区","productPrice":3888,"productUnit":"元","redbagAmount":0,"refundAmount":0,"regionLocation":"北京市","remark":"源套餐编号:S20161222105511037 源套餐状态: 2","shareNo":"","shopOrgId":744,"skuId":16978,"storageId":739,"toBePaid":3128,"toBoInvoiceAmount":0,"totalAmount":3888,"totalOrdersNo":"Z201612221055432517","tradeNo":"","transferPhone":""}
     *//*

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
        *//**
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
         *//*

        private String address;
        private String areaCounty;
        private String bargaining;
        private String cancelReason;
        private String city;
        private String comments;
        private String consignee;
        private String country;
        private long createTime;
        private long creationTimestamp;
        private String customerId;
        private boolean deleteRemark;
        private String deptId;
        private String discount;
        private String email;
        private String finalAmount;
        private String freight;
        private String fullsubAmount;
        private String hadConfirmAmount;
        private String hadInvoiceAmount;
        private String id;
        private String integral;
        private String intergralAmount;
        private String invoiceState;
        private boolean isElectronicProtocol;
        private String isLocked;
        private String isSplit;
        private String junction;
        private String mobilePhone;
        private long modificationTimestamp;
        private String num;
        private String orderKind;
        private String orderPointScount;
        private String orderSource;
        private String orderSubmitStatus;
        private String orderType;
        private String ordersNo;
        private String ordersRemark;
        private String ordersState;
        private String orgId;
        private String otherReason;
        private String payWay;
        private String phone;
        private String postcode;
        private String preferential;
        private String productName;
        private String productPrice;
        private String productUnit;
        private String redbagAmount;
        private String refundAmount;
        private String regionLocation;
        private String remark;
        private String shareNo;
        private String shopOrgId;
        private String skuId;
        private String storageId;
        private String toBePaid;
        private String toBoInvoiceAmount;
        private String totalAmount;
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

        public String getBargaining() {
            return bargaining;
        }

        public void setBargaining(String bargaining) {
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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public boolean isDeleteRemark() {
            return deleteRemark;
        }

        public void setDeleteRemark(boolean deleteRemark) {
            this.deleteRemark = deleteRemark;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(String finalAmount) {
            this.finalAmount = finalAmount;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getFullsubAmount() {
            return fullsubAmount;
        }

        public void setFullsubAmount(String fullsubAmount) {
            this.fullsubAmount = fullsubAmount;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIntergralAmount() {
            return intergralAmount;
        }

        public void setIntergralAmount(String intergralAmount) {
            this.intergralAmount = intergralAmount;
        }

        public String getInvoiceState() {
            return invoiceState;
        }

        public void setInvoiceState(String invoiceState) {
            this.invoiceState = invoiceState;
        }

        public boolean isIsElectronicProtocol() {
            return isElectronicProtocol;
        }

        public void setIsElectronicProtocol(boolean isElectronicProtocol) {
            this.isElectronicProtocol = isElectronicProtocol;
        }

        public String getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(String isLocked) {
            this.isLocked = isLocked;
        }

        public String getIsSplit() {
            return isSplit;
        }

        public void setIsSplit(String isSplit) {
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

        public String getOrderPointScount() {
            return orderPointScount;
        }

        public void setOrderPointScount(String orderPointScount) {
            this.orderPointScount = orderPointScount;
        }

        public String getOrderSource() {
            return orderSource;
        }

        public void setOrderSource(String orderSource) {
            this.orderSource = orderSource;
        }

        public String getOrderSubmitStatus() {
            return orderSubmitStatus;
        }

        public void setOrderSubmitStatus(String orderSubmitStatus) {
            this.orderSubmitStatus = orderSubmitStatus;
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

        public String getOrdersRemark() {
            return ordersRemark;
        }

        public void setOrdersRemark(String ordersRemark) {
            this.ordersRemark = ordersRemark;
        }

        public String getOrdersState() {
            return ordersState;
        }

        public void setOrdersState(String ordersState) {
            this.ordersState = ordersState;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOtherReason() {
            return otherReason;
        }

        public void setOtherReason(String otherReason) {
            this.otherReason = otherReason;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
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

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductUnit() {
            return productUnit;
        }

        public void setProductUnit(String productUnit) {
            this.productUnit = productUnit;
        }

        public String getRedbagAmount() {
            return redbagAmount;
        }

        public void setRedbagAmount(String redbagAmount) {
            this.redbagAmount = redbagAmount;
        }

        public String getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(String refundAmount) {
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

        public String getShopOrgId() {
            return shopOrgId;
        }

        public void setShopOrgId(String shopOrgId) {
            this.shopOrgId = shopOrgId;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getStorageId() {
            return storageId;
        }

        public void setStorageId(String storageId) {
            this.storageId = storageId;
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
    }*/
}
