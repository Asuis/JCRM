package com.jc.crm.form.opportunity;

/**
 * 为返回商业机会阶段信息创建的实体类
 * @author currysss 2018-11-25
 * */
public class BusinessOpportunityStageSelectVo {

    private Integer oppStageId;

    private String stageName;

    public Integer getOppStageId() {
        return oppStageId;
    }

    public void setOppStageId(Integer oppStageId) {
        this.oppStageId = oppStageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

}
