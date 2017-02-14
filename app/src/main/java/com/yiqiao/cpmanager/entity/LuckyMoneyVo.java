package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/31.
 */

public class LuckyMoneyVo implements Serializable{

    /**
     * currentPage : 1
     * numPerPage : 10
     * totalCount : 2
     * recordList : [{"redAmount":100,"createTime":1482469312000,"activityName":"李一凡满减专用","redType":1,"orderNo":4},{"redAmount":100,"createTime":1482469312000,"activityName":"李一凡满减专用","redType":1,"orderNo":5}]
     * pageCount : 1
     * beginPageIndex : 1
     * endPageIndex : 1
     * redPackageBalance : 200
     * redPackageSumMoney : 200
     * redPackageUsedSumMoney : 0
     */

    private int currentPage;
    private int numPerPage;
    private int totalCount;
    private int pageCount;
    private int beginPageIndex;
    private int endPageIndex;
    private int redPackageBalance;
    private int redPackageSumMoney;
    private int redPackageUsedSumMoney;
    private List<RecordListBean> recordList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public int getRedPackageBalance() {
        return redPackageBalance;
    }

    public void setRedPackageBalance(int redPackageBalance) {
        this.redPackageBalance = redPackageBalance;
    }

    public int getRedPackageSumMoney() {
        return redPackageSumMoney;
    }

    public void setRedPackageSumMoney(int redPackageSumMoney) {
        this.redPackageSumMoney = redPackageSumMoney;
    }

    public int getRedPackageUsedSumMoney() {
        return redPackageUsedSumMoney;
    }

    public void setRedPackageUsedSumMoney(int redPackageUsedSumMoney) {
        this.redPackageUsedSumMoney = redPackageUsedSumMoney;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public static class RecordListBean {
        /**
         * redAmount : 100
         * createTime : 1482469312000
         * activityName : 李一凡满减专用
         * redType : 1
         * orderNo : 4
         */

        private int redAmount;
        private long createTime;
        private String activityName;
        private int redType;
        private int orderNo;

        public int getRedAmount() {
            return redAmount;
        }

        public void setRedAmount(int redAmount) {
            this.redAmount = redAmount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getRedType() {
            return redType;
        }

        public void setRedType(int redType) {
            this.redType = redType;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }
    }
}
