package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/31.
 */

public class CouponVo implements Serializable{

    /**
     * currentPage : 1
     * numPerPage : 10
     * totalCount : 5
     * recordList : [{"amount":10,"getTime":1480573550000,"customerId":"662084","startDate":1480577391000,"endDate":1481177693000,"useStatus":0,"customerName":"12332","couponId":"389","conditions":"33.00","shopType":"0","goods":0,"goodsTypes":"[1][2][3]","goodsTypesInfo":"全部商品"},{"amount":10,"getTime":1481704756000,"customerId":"662084","startDate":1480577391000,"endDate":1481177693000,"useStatus":2,"couponId":"389","conditions":"33.00","shopType":"0","goods":0,"goodsTypes":"[1][2][3]","goodsTypesInfo":"全部商品"},{"amount":10,"customerId":"662084","startDate":1480573045000,"endDate":1482992250000,"useStatus":0,"customerName":"12332","couponId":"389","conditions":"33.00","shopType":"0","goods":0,"goodsTypes":"[1][2][3]","goodsTypesInfo":"全部商品"},{"amount":10,"getTime":1480573560000,"customerId":"662084","startDate":1480572989000,"endDate":1482249600000,"useStatus":0,"customerName":"12332","couponId":"389","conditions":"33.00","shopType":"0","goods":0,"goodsTypes":"[1][2][3]","goodsTypesInfo":"全部商品"},{"amount":10,"getTime":1480573557000,"customerId":"662084","startDate":1480577391000,"endDate":1481846400000,"useStatus":0,"customerName":"12332","couponId":"389","conditions":"33.00","shopType":"0","goods":0,"goodsTypes":"[1][2][3]","goodsTypesInfo":"全部商品"}]
     * pageCount : 1
     * beginPageIndex : 1
     * endPageIndex : 1
     */

    private String currentPage;
    private String numPerPage;
    private String totalCount;
    private String pageCount;
    private String beginPageIndex;
    private String endPageIndex;
    private List<RecordListBean> recordList;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(String numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(String beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public String getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(String endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public static class RecordListBean implements Serializable{
        /**
         * amount : 10
         * getTime : 1480573550000
         * customerId : 662084
         * startDate : 1480577391000
         * endDate : 1481177693000
         * useStatus : 0
         * customerName : 12332
         * couponId : 389
         * conditions : 33.00
         * shopType : 0
         * goods : 0
         * goodsTypes : [1][2][3]
         * goodsTypesInfo : 全部商品
         */

        private String amount;
        private long getTime;
        private String customerId;
        private long startDate;
        private long endDate;
        private String useStatus;
        private String customerName;
        private String couponId;
        private String conditions;
        private String shopType;
        private String goods;
        private String goodsTypes;
        private String goodsTypesInfo;
        private String isToOverEndTime;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public long getGetTime() {
            return getTime;
        }

        public void setGetTime(long getTime) {
            this.getTime = getTime;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public String getUseStatus() {
            return useStatus;
        }

        public void setUseStatus(String useStatus) {
            this.useStatus = useStatus;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getConditions() {
            return conditions;
        }

        public void setConditions(String conditions) {
            this.conditions = conditions;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getGoodsTypes() {
            return goodsTypes;
        }

        public void setGoodsTypes(String goodsTypes) {
            this.goodsTypes = goodsTypes;
        }

        public String getGoodsTypesInfo() {
            return goodsTypesInfo;
        }

        public void setGoodsTypesInfo(String goodsTypesInfo) {
            this.goodsTypesInfo = goodsTypesInfo;
        }

        public String getIsToOverEndTime() {
            return isToOverEndTime;
        }

        public void setIsToOverEndTime(String isToOverEndTime) {
            this.isToOverEndTime = isToOverEndTime;
        }
    }
}
