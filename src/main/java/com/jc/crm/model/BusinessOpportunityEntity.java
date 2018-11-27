package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 商业机会表(business_opp)实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunityEntity {

    private Integer businessOppId;

    private String oppName;

    private String description;

    private Integer oppStageId;

    private Integer oppSourceId;

    private Integer oppAccountMoneyId;

    private Integer oppLossReasonId;

    private Integer holder;

    private Integer executor;

    private Integer cid;

    private Integer importantLevel;

    private Integer roiAnalysisCompleted;

    private Integer budgetConfirmed;

    private Integer isCompleted;

    private Integer isDeleted;

    private Integer possibility;

    private String nextStep;

    private String ex1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date utime;

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOppStageId() {
        return oppStageId;
    }

    public void setOppStageId(Integer oppStageId) {
        this.oppStageId = oppStageId;
    }

    public Integer getOppSourceId() { return oppSourceId; }

    public void setOppSourceId(Integer oppSourceId) {
        this.oppSourceId = oppSourceId;
    }

    public Integer getOppAccountMoneyId() {
        return oppAccountMoneyId;
    }

    public void setOppAccountMoneyId(Integer oppAccountMoneyId) {
        this.oppAccountMoneyId = oppAccountMoneyId;
    }

    public Integer getOppLossReasonId() {
        return oppLossReasonId;
    }

    public void setOppLossReasonId(Integer oppLossReasonId) {
        this.oppLossReasonId = oppLossReasonId;
    }

    public Integer getHolder() {
        return holder;
    }

    public void setHolder(Integer holder) {
        this.holder = holder;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public Integer getCid() { return cid; }

    public void setCid(Integer cid) { this.cid = cid; }

    public Integer getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(Integer importantLevel) {
        this.importantLevel = importantLevel;
    }

    public Integer getRoiAnalysisCompleted() {
        return roiAnalysisCompleted;
    }

    public void setRoiAnalysisCompleted(Integer roiAnalysisCompleted) { this.roiAnalysisCompleted = roiAnalysisCompleted; }

    public Integer getBudgetConfirmed() {
        return budgetConfirmed;
    }

    public void setBudgetConfirmed(Integer budgetConfirmed) {
        this.budgetConfirmed = budgetConfirmed;
    }

    public Integer getIsCompleted() { return isCompleted; }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Integer getIsDeleted() { return isDeleted; }

    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }

    public Integer getPossibility() {
        return possibility;
    }

    public void setPossibility(Integer possibility) {
        this.possibility = possibility;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

}
