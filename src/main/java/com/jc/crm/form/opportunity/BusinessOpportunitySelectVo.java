package com.jc.crm.form.opportunity;

import com.jc.crm.model.BusinessOpportunityApplicationEntity;
import com.jc.crm.model.BusinessOpportunityEntity;

import java.util.List;

/**
 * 为返回含有申请信息的商业机会信息创建的实体类
 * @author currysss 2018-11-28
 * */
public class BusinessOpportunitySelectVo extends BusinessOpportunityEntity {

    private String username;

    private List<BusinessOpportunityApplicationEntity> applicationVoList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BusinessOpportunityApplicationEntity> getApplicationVoList() {
        return applicationVoList;
    }

    public void setApplicationVoList(List<BusinessOpportunityApplicationEntity> applicationVoList) {
        this.applicationVoList = applicationVoList;
    }

}
