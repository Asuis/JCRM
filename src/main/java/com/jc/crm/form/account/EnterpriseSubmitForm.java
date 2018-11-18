package com.jc.crm.form.account;

import com.jc.crm.bean.Address;

/**
 * @desc 用于提交企业信息
 * */
public class EnterpriseSubmitForm {
    private String enterpriseName;
    private Address address;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
