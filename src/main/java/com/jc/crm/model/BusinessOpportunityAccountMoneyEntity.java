package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 商业机会价值表(business_opp_account_money)实体类
 * @author currysss 2018-11-23
 * */
public class BusinessOpportunityAccountMoneyEntity {

    private Integer oppAccountMoneyId;

    private Integer businessOppId;

    private String accountMoney;

    private String description;

    private String ex1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date utime;

    public Integer getOppAccountMoneyId() {
        return oppAccountMoneyId;
    }

    public void setOppAccountMoneyId(Integer oppAccountMoneyId) {
        this.oppAccountMoneyId = oppAccountMoneyId;
    }

    public Integer getBusinessOppId() { return businessOppId; }

    public void setBusinessOppId(Integer businessOppId) { this.businessOppId = businessOppId; }

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

}
