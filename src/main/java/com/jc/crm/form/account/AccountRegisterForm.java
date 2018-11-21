package com.jc.crm.form.account;

import com.jc.crm.bean.Address;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.JoinDepartmentForm;
import com.jc.crm.model.DepartmentEntity;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author asuis
 */
public class AccountRegisterForm {
    @Email(message = "message.email不是邮箱格式")
    private String username;
    @NotBlank(message = "密码为空")
    @Size(min = 8, max = 32, message = "密码长度要求在8-32位之间")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,32}$", message = "密码不能全部由字母和数字组成")
    private String pass;
    @Valid
    private AddressForm address;
    @Valid
    private JoinDepartmentForm department;
}
