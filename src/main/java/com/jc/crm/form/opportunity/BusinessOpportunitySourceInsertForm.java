package com.jc.crm.form.opportunity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 为添加商业机会的市场来源创建的实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunitySourceInsertForm {

    @NotBlank(message = "市场来源名称不能为空")
    @Size(min = 2, max = 20 , message = "填写内容范围2-20")
    private String sourceName;

    @NotBlank(message = "市场来源描述不能为空")
    @Size(min = 10, max = 100 , message = "填写内容范围10-100")
    private String description;

    private Integer statusId;

    private Integer typeId;

    private Integer sendCount;

    private Integer budgetCost;

    private Integer actualCost;

    private Integer expectedIncome;

    private Double responsePercentage;

    private String ex1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date edate;

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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getBudgetCost() {
        return budgetCost;
    }

    public void setBudgetCost(Integer budgetCost) {
        this.budgetCost = budgetCost;
    }

    public Integer getActualCost() {
        return actualCost;
    }

    public void setActualCost(Integer actualCost) {
        this.actualCost = actualCost;
    }

    public Integer getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Integer expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getResponsePercentage() {
        return responsePercentage;
    }

    public void setResponsePercentage(Double responsePercentage) {
        this.responsePercentage = responsePercentage;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

}
