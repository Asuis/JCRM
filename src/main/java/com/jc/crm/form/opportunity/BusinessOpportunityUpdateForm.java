package com.jc.crm.form.opportunity;

/**
 * 为机会所有者修改商业机会信息创建的实体类
 * @author currysss 2018-11-26
 * */
public class BusinessOpportunityUpdateForm extends BusinessOpportunityInsertForm{

    private Integer businessOppId;

    private Integer oppLossReasonId;

    public Integer getBusinessOppId() { return businessOppId; }

    public void setBusinessOppId(Integer businessOppId) { this.businessOppId = businessOppId; }

    public Integer getOppLossReasonId() {
        return oppLossReasonId;
    }

    public void setOppLossReasonId(Integer oppLossReasonId) {
        this.oppLossReasonId = oppLossReasonId;
    }

}
