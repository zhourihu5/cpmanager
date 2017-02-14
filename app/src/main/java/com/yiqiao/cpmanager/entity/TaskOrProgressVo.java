package com.yiqiao.cpmanager.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xu on 2017/1/12.
 */

public class TaskOrProgressVo implements Serializable {

    private List<NoOverTaskListBean> noOverTaskList;
    private List<ServiceVo> progressList;
    private List<RecommendVo> recommendVoList;

    public List<NoOverTaskListBean> getNoOverTaskList() {
        return noOverTaskList;
    }

    public void setNoOverTaskList(List<NoOverTaskListBean> noOverTaskList) {
        this.noOverTaskList = noOverTaskList;
    }

    public List<ServiceVo> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<ServiceVo> progressList) {
        this.progressList = progressList;
    }

    public List<RecommendVo> getRecommendVoList() {
        return recommendVoList;
    }

    public void setRecommendVoList(List<RecommendVo> recommendVoList) {
        this.recommendVoList = recommendVoList;
    }

    public static class NoOverTaskListBean {
        /**
         * id : 1
         * version : 0
         * createTime : 1483366880000
         * name : 新手任务
         * taskType : 0
         * updateTime : 1485440485000
         * deleteFlag : 0
         * status : 0
         */

        private int id;
        private int version;
        private long createTime;
        private String name;
        private int taskType;
        private long updateTime;
        private int deleteFlag;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

//    public static class RecommendVoListBean {
//        /**
//         * id : 72
//         * title : 进出口企业代理记账（内资）
//         * platformType : 2
//         * href : 5854
//         * remark : 进出口企业代理记账（内资）
//         * position1 : 1
//         * position2 : 2
//         * imageHref :
//         * sort : 5
//         * price : 1000
//         * createCustomerId : 1
//         * createTimeLong : 1483954856000
//         * deleteStatus : 0
//         */
//
//        private int id;
//        private String title;
//        private int platformType;
//        private String href;
//        private String remark;
//        private int position1;
//        private int position2;
//        private String imageHref;
//        private int sort;
//        private String price;
//        private int createCustomerId;
//        private long createTimeLong;
//        private int deleteStatus;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public int getPlatformType() {
//            return platformType;
//        }
//
//        public void setPlatformType(int platformType) {
//            this.platformType = platformType;
//        }
//
//        public String getHref() {
//            return href;
//        }
//
//        public void setHref(String href) {
//            this.href = href;
//        }
//
//        public String getRemark() {
//            return remark;
//        }
//
//        public void setRemark(String remark) {
//            this.remark = remark;
//        }
//
//        public int getPosition1() {
//            return position1;
//        }
//
//        public void setPosition1(int position1) {
//            this.position1 = position1;
//        }
//
//        public int getPosition2() {
//            return position2;
//        }
//
//        public void setPosition2(int position2) {
//            this.position2 = position2;
//        }
//
//        public String getImageHref() {
//            return imageHref;
//        }
//
//        public void setImageHref(String imageHref) {
//            this.imageHref = imageHref;
//        }
//
//        public int getSort() {
//            return sort;
//        }
//
//        public void setSort(int sort) {
//            this.sort = sort;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public int getCreateCustomerId() {
//            return createCustomerId;
//        }
//
//        public void setCreateCustomerId(int createCustomerId) {
//            this.createCustomerId = createCustomerId;
//        }
//
//        public long getCreateTimeLong() {
//            return createTimeLong;
//        }
//
//        public void setCreateTimeLong(long createTimeLong) {
//            this.createTimeLong = createTimeLong;
//        }
//
//        public int getDeleteStatus() {
//            return deleteStatus;
//        }
//
//        public void setDeleteStatus(int deleteStatus) {
//            this.deleteStatus = deleteStatus;
//        }
//    }
}
