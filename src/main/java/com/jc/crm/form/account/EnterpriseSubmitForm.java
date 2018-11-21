package com.jc.crm.form.account;

import com.jc.crm.form.AddressForm;
import com.jc.crm.validator.anootation.Address;
import com.jc.crm.validator.anootation.enterprise.EnterpriseName;

import javax.validation.Valid;

/**
 * @desc 用于提交企业信息
 * */
public class EnterpriseSubmitForm {
    @EnterpriseName
    private String enterpriseName;
    @Valid
    private AddressForm address;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }
}
