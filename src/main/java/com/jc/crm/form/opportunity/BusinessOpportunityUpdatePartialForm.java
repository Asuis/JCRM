package com.jc.crm.form.opportunity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 为机会跟进者修改商业机会信息创建的实体类
 * @author currysss 2018-11-26
 * */
public class BusinessOpportunityUpdatePartialForm {

    private Integer businessOppId;

    private Integer oppLossReasonId;

    private Integer oppStageId;

    private Integer roiAnalysisCompleted;

    private Integer budgetConfirmed;

    private Integer isCompleted;

    private Integer possibility;

    @NotBlank(message = "下一步要做什么的内容不能为空")
    @Size(min = 5, max = 15 , message = "填写内容范围5-15")
    private String nextStep;

    @Size(max = 200 , message = "填写内容长度不能超过200")
    private String ex1;

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public Integer getOppLossReasonId() {
        return oppLossReasonId;
    }

    public void setOppLossReasonId(Integer oppLossReasonId) {
        this.oppLossReasonId = oppLossReasonId;
    }

    public Integer getOppStageId() {
        return oppStageId;
    }

    public void setOppStageId(Integer oppStageId) {
        this.oppStageId = oppStageId;
    }

    public Integer getRoiAnalysisCompleted() {
        return roiAnalysisCompleted;
    }

    public void setRoiAnalysisCompleted(Integer roiAnalysisCompleted) {
        this.roiAnalysisCompleted = roiAnalysisCompleted;
    }

    public Integer getBudgetConfirmed() {
        return budgetConfirmed;
    }

    public void setBudgetConfirmed(Integer budgetConfirmed) {
        this.budgetConfirmed = budgetConfirmed;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

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

}
