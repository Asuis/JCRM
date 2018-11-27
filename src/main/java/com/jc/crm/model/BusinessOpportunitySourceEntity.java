package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 商业机会来源表(business_opp_source)实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunitySourceEntity {

    private Integer oppSourceId;

    private String sourceName;

    private String description;

    private Integer statusId;

    private Integer typeId;

    private Integer holder;

    private Integer sendCount;

    private Integer budgetCost;

    private Integer actualCost;

    private Integer expectedIncome;

    private Double responsePercentage;

    private Integer weight;

    private String ex1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date edate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date utime;

    public Integer getOppSourceId() {
        return oppSourceId;
    }

    public void setOppSourceId(Integer oppSourceId) {
        this.oppSourceId = oppSourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusId() { return statusId; }

    public void setStatusId(Integer statusId) { this.statusId = statusId; }

    public Integer getTypeId() { return typeId; }

    public void setTypeId(Integer typeId) { this.typeId = typeId; }

    public Integer getHolder() { return holder; }

    public void setHolder(Integer holder) { this.holder = holder; }

    public Integer getSendCount() { return sendCount; }

    public void setSendCount(Integer sendCount) { this.sendCount = sendCount; }

    public Integer getBudgetCost() { return budgetCost; }

    public void setBudgetCost(Integer budgetCost) { this.budgetCost = budgetCost; }

    public Integer getActualCost() { return actualCost; }

    public void setActualCost(Integer actualCost) { this.actualCost = actualCost; }

    public Integer getExpectedIncome() { return expectedIncome; }

    public void setExpectedIncome(Integer expectedIncome) { this.expectedIncome = expectedIncome; }

    public Double getResponsePercentage() { return responsePercentage; }

    public void setResponsePercentage(Double responsePercentage) { this.responsePercentage = responsePercentage; }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) { this.weight = weight; }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public Date getSdate() { return sdate; }

    public void setSdate(Date sdate) { this.sdate = sdate; }

    public Date getEdate() { return edate; }

    public void setEdate(Date edate) { this.edate = edate; }

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
