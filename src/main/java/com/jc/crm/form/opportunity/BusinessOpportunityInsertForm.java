package com.jc.crm.form.opportunity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 为添加商业机会信息创建的实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunityInsertForm {

    @NotBlank(message = "商业机会名称不能为空")
    @Size(min = 2, max = 20 , message = "填写内容范围2-20")
    private String oppName;

    @NotBlank(message = "商业机会描述不能为空")
    @Size(min = 10, max = 100 , message = "填写内容范围10-100")
    private String oppDescription;

    private Integer executor;

    private Integer cid;

    private Integer oppSourceId;

    private Integer oppStageId;

    @NotBlank(message = "金额不能为空")
    private String accountMoney;

    private Integer importantLevel;

    private Integer roiAnalysisCompleted;

    private Integer budgetConfirmed;

    private Integer isCompleted;

    private Integer possibility;

    @NotBlank(message = "下一步要做什么的内容不能为空")
    @Size(min = 5, max = 15 , message = "填写内容范围5-15")
    private String nextStep;

    @Size(max = 200 , message = "填写内容长度不能超过200")
    private String oppEx1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date deadline;

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }

    public String getOppDescription() {
        return oppDescription;
    }

    public void setOppDescription(String oppDescription) {
        this.oppDescription = oppDescription;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public Integer getCid() { return cid; }

    public void setCid(Integer cid) { this.cid = cid; }

    public Integer getOppSourceId() { return oppSourceId; }

    public void setOppSourceId(Integer oppSourceId) { this.oppSourceId = oppSourceId; }

    public Integer getOppStageId() { return oppStageId; }

    public void setOppStageId(Integer oppStageId) { this.oppStageId = oppStageId; }

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

    public void setBudgetConfirmed(Integer budgetConfirmed) { this.budgetConfirmed = budgetConfirmed; }

    public Integer getIsCompleted() { return isCompleted; }

    public void setIsCompleted(Integer isCompleted) { this.isCompleted = isCompleted; }

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

    public String getOppEx1() {
        return oppEx1;
    }

    public void setOppEx1(String oppEx1) {
        this.oppEx1 = oppEx1;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getAccountMoney() { return accountMoney; }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }


}
