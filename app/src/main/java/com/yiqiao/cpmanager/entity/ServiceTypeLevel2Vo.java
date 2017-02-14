package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2016/12/30.
 */

public class ServiceTypeLevel2Vo implements Serializable{

    /**
     * sortName : 财税代办1
     * parentId : 5
     * id : 6
     * productTypeThree : [{"id":2,"version":0,"sortName":"B","parentId":6,"sortCode":1,"isShow":1,"categoryDescription":"1","loadType":"1","level":3,"preId":"1","deleteFlag":0,"updateTime":1483699758000},{"id":17,"version":0,"createTime":1482982000000,"sortName":"1212","parentId":6,"sortCode":33,"isShow":1,"categoryDescription":"1","loadType":"1","level":2,"preId":"1","deleteFlag":0,"updateTime":1482982086000,"typeImage":""}]
     */

    private String sortName;
    private String parentId;
    private String id;
    private List<ProductTypeThreeBean> productTypeThree;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductTypeThreeBean> getProductTypeThree() {
        return productTypeThree;
    }

    public void setProductTypeThree(List<ProductTypeThreeBean> productTypeThree) {
        this.productTypeThree = productTypeThree;
    }

    public static class ProductTypeThreeBean {
        /**
         * id : 2
         * version : 0
         * sortName : B
         * parentId : 6
         * sortCode : 1
         * isShow : 1
         * categoryDescription : 1
         * loadType : 1
         * level : 3
         * preId : 1
         * deleteFlag : 0
         * updateTime : 1483699758000
         * createTime : 1482982000000
         * typeImage :
         */

        private String id;
        private String version;
        private String sortName;
        private String parentId;
        private String sortCode;
        private String isShow;
        private String categoryDescription;
        private String loadType;
        private String level;
        private String preId;
        private String deleteFlag;
        private long updateTime;
        private long createTime;
        private String typeImage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getSortCode() {
            return sortCode;
        }

        public void setSortCode(String sortCode) {
            this.sortCode = sortCode;
        }

        public String getIsShow() {
            return isShow;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getCategoryDescription() {
            return categoryDescription;
        }

        public void setCategoryDescription(String categoryDescription) {
            this.categoryDescription = categoryDescription;
        }

        public String getLoadType() {
            return loadType;
        }

        public void setLoadType(String loadType) {
            this.loadType = loadType;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPreId() {
            return preId;
        }

        public void setPreId(String preId) {
            this.preId = preId;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getTypeImage() {
            return typeImage;
        }

        public void setTypeImage(String typeImage) {
            this.typeImage = typeImage;
        }
    }
}
