package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/19.
 */

public class SearchSkuRequestVo implements Serializable{

    public String getAttrLevel1() {
        return attrLevel1;
    }

    public void setAttrLevel1(String attrLevel1) {
        this.attrLevel1 = attrLevel1;
    }

    public String getAttrLevel3() {
        return attrLevel3;
    }

    public void setAttrLevel3(String attrLevel3) {
        this.attrLevel3 = attrLevel3;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    private String attrLevel1;
    private String attrLevel3;
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private int pageNum;
    private int pageSize;


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
