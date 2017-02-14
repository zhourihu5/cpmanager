package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/30.
 */

public class SkuListVo implements Serializable{

    /**
     * currentPage : 1
     * numPerPage : 10
     * totalCount : 4
     * recordList : [{"tid":210,"name":"谭亚珍测试一个代理记账","price":1,"type":1},{"tid":174,"name":"内资公司注册","price":0.01,"type":1},{"tid":91,"name":"SPUlm","price":100,"type":1},{"tid":74,"name":"刘宝专用SPU","price":5,"type":1}]
     * pageCount : 1
     * beginPageIndex : 1
     * endPageIndex : 1
     */

    private int currentPage;
    private int numPerPage;
    private int totalCount;
    private int pageCount;
    private int beginPageIndex;
    private int endPageIndex;
    private List<SkuVo> recordList;

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

    public List<SkuVo> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<SkuVo> recordList) {
        this.recordList = recordList;
    }

//    public static class RecordListBean {
//        /**
//         * tid : 210
//         * name : 谭亚珍测试一个代理记账
//         * price : 1
//         * type : 1
//         */
//
//        private int tid;
//        private String name;
//        private int price;
//        private int type;
//
//        public int getTid() {
//            return tid;
//        }
//
//        public void setTid(int tid) {
//            this.tid = tid;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getPrice() {
//            return price;
//        }
//
//        public void setPrice(int price) {
//            this.price = price;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//    }
}
