package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 商业机会完成记录表(business_record)实体类
 * @author currysss 2018-11-23
 * */
public class BusinessRecordEntity {

    private Integer oppRecordId;

    private String description;

    private Integer businessOppId;

    private String ex1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ctime;

    public Integer getOppRecordId() {
        return oppRecordId;
    }

    public void setOppRecordId(Integer oppRecordId) {
        this.oppRecordId = oppRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBusinessOppId() {
        return businessOppId;
    }

    public void setBusinessOppId(Integer businessOppId) {
        this.businessOppId = businessOppId;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

}

