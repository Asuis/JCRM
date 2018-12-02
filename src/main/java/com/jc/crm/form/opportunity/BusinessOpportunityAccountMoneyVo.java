package com.jc.crm.form.opportunity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 为返回商业机会价值图表而创建的实体类
 * @author currysss 2018-12-2
 * */
public class BusinessOpportunityAccountMoneyVo {

    private String accountMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
