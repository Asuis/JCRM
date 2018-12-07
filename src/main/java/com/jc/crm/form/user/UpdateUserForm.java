package com.jc.crm.form.user;

import com.jc.crm.form.AddressForm;
import com.jc.crm.service.department.DepartmentService;

/**
 * @author asuis
 * @version: UpdateUserForm.java 18-12-7:下午4:02
 */
public class UpdateUserForm {
    private Integer uid;
    private String username;
    private String signature;
    private Integer departmentId;
    private Integer phone;
    private AddressForm address;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }
}
