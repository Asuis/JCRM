package com.jc.crm.form.account;

import com.jc.crm.form.AddressForm;
import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.model.UserEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author asuis
 */
public class UserUpdateForm {
    private String username;
    private String signature;
    @Valid
    private AddressForm address;
    private String phone;
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

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
