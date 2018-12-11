package com.jc.crm.form.opportunity;

/**
 * 为返回商业机会市场来源信息创建的实体类
 * @author currysss 2018-11-25
 * */
public class BusinessOpportunitySourceSelectVo {

    private Integer oppSourceId;

    private String sourceName;

    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
