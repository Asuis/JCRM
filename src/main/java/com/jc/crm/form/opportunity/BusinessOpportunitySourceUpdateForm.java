package com.jc.crm.form.opportunity;

/**
 * 为修改商业机会的市场来源创建的实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunitySourceUpdateForm extends BusinessOpportunitySourceInsertForm{

    private Integer oppSourceId;

    public Integer getOppSourceId() {
        return oppSourceId;
    }

    public void setOppSourceId(Integer oppSourceId) {
        this.oppSourceId = oppSourceId;
    }

}
