package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 商业机会申请表(business_opp_application)实体类
 * @author currysss 2018-11-24
 * */
public class BusinessOpportunityApplicationEntity {

    private Integer oppApplicationId;

    private Integer businessOppId;

    private String accountMoney;

    private String applicationReason;

    private String rejectionReason;

    private String ex1;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deadline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date utime;

    public Integer getOppApplicationId() {
        return oppApplicationId;
    }

    public void setOppApplicationId(Integer oppApplicationId) {
        this.oppApplicationId = oppApplicationId;
    }

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
