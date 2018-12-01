package com.jc.crm.form.opportunity;

/**
 * 为修改商业机会状态创建的实体类
 * @author currysss 2018-11-27
 * */
public class BusinessOpportunityDeleteForm {

    private Integer businessOppId;

    private Integer isDeleted;

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
