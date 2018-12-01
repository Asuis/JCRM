package com.jc.crm.form.opportunity;

/**
 * 为同意商业机会修改申请创建的实体类
 * @author currysss 2018-11-27
 * */
public class BusinessOpportunityAgreeApplicationForm {

    private Integer businessOppId;

    private Integer oppApplicationId;

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public Integer getOppApplicationId() { return oppApplicationId; }

    public void setOppApplicationId(Integer oppApplicationId) { this.oppApplicationId = oppApplicationId; }

}
