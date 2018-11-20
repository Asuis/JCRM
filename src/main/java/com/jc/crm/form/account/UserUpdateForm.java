package com.jc.crm.form.account;

import com.jc.crm.form.AddressForm;
import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.form.enterprise.EnterpriseForm;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

public class UserUpdateForm {
    private String username;
    @Email(message = "email格式不正确")
    private String email;
    @Valid
    private ContactForm contact;
    @Valid
    private AddressForm address;
    @Valid
    private EnterpriseForm enterprise;
    @Past
    private Date createTime;
    @Past
    private Date lastLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactForm getContact() {
        return contact;
    }

    public void setContact(ContactForm contact) {
        this.contact = contact;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public EnterpriseForm getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseForm enterprise) {
        this.enterprise = enterprise;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
