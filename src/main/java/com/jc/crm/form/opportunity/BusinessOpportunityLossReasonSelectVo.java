package com.jc.crm.form.opportunity;

/**
 * 为返回商业机会丢失原因信息创建的实体类
 * @author currysss 2018-11-25
 * */
public class BusinessOpportunityLossReasonSelectVo {

    private Integer oppLossReasonId;

    private String reasonName;

    public Integer getOppLossReasonId() {
        return oppLossReasonId;
    }

    public void setOppLossReasonId(Integer oppLossReasonId) {
        this.oppLossReasonId = oppLossReasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

}
