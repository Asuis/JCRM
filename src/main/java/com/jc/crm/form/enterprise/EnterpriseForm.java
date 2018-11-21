package com.jc.crm.form.enterprise;

import com.jc.crm.form.AddressForm;
import com.jc.crm.model.EnterpriseEntity;
import com.jc.crm.utils.TimeUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author asuis
 */
public class EnterpriseForm {
    @Size(min = 1, max = 64)
    private String enterpriseName;
    @Pattern(message = "not phone number", regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$")
    private String phone;
    @Size(max = 500, message = "描述不能超过500")
    private String description;
    private String avatar;
    @NotNull
    @Valid
    private AddressForm address;
    @NotNull
    private Integer admin;
    @Size(max = 2048)
    private String ex1;
    @Size(max = 2048)
    private String ex2;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1;
    }

    public String getEx2() {
        return ex2;
    }

    public void setEx2(String ex2) {
        this.ex2 = ex2;
    }

    public EnterpriseEntity toEnterpriseEntity(Integer addressId) {
        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        enterpriseEntity.setAdmin(this.getAdmin());
        enterpriseEntity.setAvatar(this.getAvatar());
        enterpriseEntity.setDescription(this.getDescription());
        enterpriseEntity.setPhone(this.getPhone());
        enterpriseEntity.setEnterpriseName(this.getEnterpriseName());
        Date now = TimeUtils.getNowTime();
        enterpriseEntity.setCtime(now);
        enterpriseEntity.setUtime(now);
        return enterpriseEntity;
    }
}
