package com.yiqiao.cpmanager.entity;

import java.io.Serializable;

/**
 * Created by Xu on 2016/12/23.
 */

public class ServiceVo implements Serializable{

    /**
     * agreementId : 2756
     * bState : 3
     * beginDate : 1472659200000
     * contactName : 曹经理
     * contactPhone : 13200000071
     * creationTimestamp : 1482471249306
     * deadline : 1
     * deleteRemark : false
     * frequencyCount : 1
     * frequencyType : 1
     * id : 768
     * isEnd : 0
     * isOverdue : 0
     * now_step : 1
     * orgName : 无锡市梁溪区崇安寺店
     * serviceName : 工商服务-变更登记
     * serviceSetId : 3593
     * step_count : 4
     * triggerType : 1
     */

    private int agreementId;
    private int bState;
    private long beginDate;
    private String contactName;
    private String contactPhone;
    private long creationTimestamp;
    private int deadline;
    private boolean deleteRemark;
    private int frequencyCount;
    private String frequencyType;
    private int id;
    private int isEnd;
    private int isOverdue;
    private int now_step;
    private String orgName;
    private String serviceName;
    private int serviceSetId;
    private int step_count;
    private String triggerType;

    public int getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    public int getBState() {
        return bState;
    }

    public void setBState(int bState) {
        this.bState = bState;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public boolean isDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(boolean deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public int getFrequencyCount() {
        return frequencyCount;
    }

    public void setFrequencyCount(int frequencyCount) {
        this.frequencyCount = frequencyCount;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(int isEnd) {
        this.isEnd = isEnd;
    }

    public int getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(int isOverdue) {
        this.isOverdue = isOverdue;
    }

    public int getNow_step() {
        return now_step;
    }

    public void setNow_step(int now_step) {
        this.now_step = now_step;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceSetId() {
        return serviceSetId;
    }

    public void setServiceSetId(int serviceSetId) {
        this.serviceSetId = serviceSetId;
    }

    public int getStep_count() {
        return step_count;
    }

    public void setStep_count(int step_count) {
        this.step_count = step_count;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }
}
