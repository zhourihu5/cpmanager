package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2017/1/10.
 */

public class StoreVo implements Serializable{

    /**
     * imagePrefix : http://xtpt12366.oss-cn-beijing.aliyuncs.com/
     * page : {"currentPage":1,"numPerPage":10,"totalCount":2,"recordList":[{"callCenterFlag":"0","contacts":"朝阳群众","creationTimestamp":1484016432511,"deleteRemark":false,"depChatUrl":"朝阳大悦城","deptAddress":"朝阳","deptAreaId":110105,"deptNO":"1001280101","deptName":"朝阳直销网点","deptType":"0","depx":"0","depy":"0","id":260,"identifyCode":"C1023","isBusiness":1,"isShow":1,"logoImg":"xtpt/default/1/433/8b7c1112-8125-4b33-88ee-8662bf9924f3/1b15d5873f8683b8681a185c5945a1e1.jpg","phone":"15811366263","phone2":"","serviceCustomerNum":1000,"sortCode":1,"workTime":"8:30-23:00"},{"callCenterFlag":"0","contacts":"海淀群众","creationTimestamp":1484016432511,"deleteRemark":false,"depChatUrl":"海淀大悦城","deptAddress":"海淀","deptAreaId":110108,"deptNO":"1001280102","deptName":"海淀直销网点","deptType":"0","depx":"0","depy":"0","id":261,"isBusiness":1,"isShow":1,"logoImg":"xtpt/default/1/433/f6d41999-428a-4b04-a500-e4634be15c59/3b0e5efaefbb9b0052c539fded8038de.jpg","phone":"13100000000","phone2":"13100000000","serviceCustomerNum":1000,"sortCode":2,"workTime":"8:30-23:30"}],"pageCount":1,"beginPageIndex":1,"endPageIndex":1}
     */

    private String imagePrefix;
    private PageBean page;

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * currentPage : 1
         * numPerPage : 10
         * totalCount : 2
         * recordList : [{"callCenterFlag":"0","contacts":"朝阳群众","creationTimestamp":1484016432511,"deleteRemark":false,"depChatUrl":"朝阳大悦城","deptAddress":"朝阳","deptAreaId":110105,"deptNO":"1001280101","deptName":"朝阳直销网点","deptType":"0","depx":"0","depy":"0","id":260,"identifyCode":"C1023","isBusiness":1,"isShow":1,"logoImg":"xtpt/default/1/433/8b7c1112-8125-4b33-88ee-8662bf9924f3/1b15d5873f8683b8681a185c5945a1e1.jpg","phone":"15811366263","phone2":"","serviceCustomerNum":1000,"sortCode":1,"workTime":"8:30-23:00"},{"callCenterFlag":"0","contacts":"海淀群众","creationTimestamp":1484016432511,"deleteRemark":false,"depChatUrl":"海淀大悦城","deptAddress":"海淀","deptAreaId":110108,"deptNO":"1001280102","deptName":"海淀直销网点","deptType":"0","depx":"0","depy":"0","id":261,"isBusiness":1,"isShow":1,"logoImg":"xtpt/default/1/433/f6d41999-428a-4b04-a500-e4634be15c59/3b0e5efaefbb9b0052c539fded8038de.jpg","phone":"13100000000","phone2":"13100000000","serviceCustomerNum":1000,"sortCode":2,"workTime":"8:30-23:30"}]
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
             * callCenterFlag : 0
             * contacts : 朝阳群众
             * creationTimestamp : 1484016432511
             * deleteRemark : false
             * depChatUrl : 朝阳大悦城
             * deptAddress : 朝阳
             * deptAreaId : 110105
             * deptNO : 1001280101
             * deptName : 朝阳直销网点
             * deptType : 0
             * depx : 0
             * depy : 0
             * id : 260
             * identifyCode : C1023
             * isBusiness : 1
             * isShow : 1
             * logoImg : xtpt/default/1/433/8b7c1112-8125-4b33-88ee-8662bf9924f3/1b15d5873f8683b8681a185c5945a1e1.jpg
             * phone : 15811366263
             * phone2 :
             * serviceCustomerNum : 1000
             * sortCode : 1
             * workTime : 8:30-23:00
             */

            private String callCenterFlag;
            private String contacts;
            private long creationTimestamp;
            private boolean deleteRemark;
            private String depChatUrl;
            private String deptAddress;
            private String deptAreaId;
            private String deptNO;
            private String deptName;
            private String deptType;
            private String depx;
            private String depy;
            private String id;
            private String identifyCode;
            private String isBusiness;
            private String isShow;
            private String logoImg;
            private String phone;
            private String phone2;
            private String serviceCustomerNum;
            private String sortCode;
            private String workTime;

            public String getCallCenterFlag() {
                return callCenterFlag;
            }

            public void setCallCenterFlag(String callCenterFlag) {
                this.callCenterFlag = callCenterFlag;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public long getCreationTimestamp() {
                return creationTimestamp;
            }

            public void setCreationTimestamp(long creationTimestamp) {
                this.creationTimestamp = creationTimestamp;
            }

            public boolean isDeleteRemark() {
                return deleteRemark;
            }

            public void setDeleteRemark(boolean deleteRemark) {
                this.deleteRemark = deleteRemark;
            }

            public String getDepChatUrl() {
                return depChatUrl;
            }

            public void setDepChatUrl(String depChatUrl) {
                this.depChatUrl = depChatUrl;
            }

            public String getDeptAddress() {
                return deptAddress;
            }

            public void setDeptAddress(String deptAddress) {
                this.deptAddress = deptAddress;
            }

            public String getDeptAreaId() {
                return deptAreaId;
            }

            public void setDeptAreaId(String deptAreaId) {
                this.deptAreaId = deptAreaId;
            }

            public String getDeptNO() {
                return deptNO;
            }

            public void setDeptNO(String deptNO) {
                this.deptNO = deptNO;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getDeptType() {
                return deptType;
            }

            public void setDeptType(String deptType) {
                this.deptType = deptType;
            }

            public String getDepx() {
                return depx;
            }

            public void setDepx(String depx) {
                this.depx = depx;
            }

            public String getDepy() {
                return depy;
            }

            public void setDepy(String depy) {
                this.depy = depy;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIdentifyCode() {
                return identifyCode;
            }

            public void setIdentifyCode(String identifyCode) {
                this.identifyCode = identifyCode;
            }

            public String getIsBusiness() {
                return isBusiness;
            }

            public void setIsBusiness(String isBusiness) {
                this.isBusiness = isBusiness;
            }

            public String getIsShow() {
                return isShow;
            }

            public void setIsShow(String isShow) {
                this.isShow = isShow;
            }

            public String getLogoImg() {
                return logoImg;
            }

            public void setLogoImg(String logoImg) {
                this.logoImg = logoImg;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone2() {
                return phone2;
            }

            public void setPhone2(String phone2) {
                this.phone2 = phone2;
            }

            public String getServiceCustomerNum() {
                return serviceCustomerNum;
            }

            public void setServiceCustomerNum(String serviceCustomerNum) {
                this.serviceCustomerNum = serviceCustomerNum;
            }

            public String getSortCode() {
                return sortCode;
            }

            public void setSortCode(String sortCode) {
                this.sortCode = sortCode;
            }

            public String getWorkTime() {
                return workTime;
            }

            public void setWorkTime(String workTime) {
                this.workTime = workTime;
            }
        }
    }
}
