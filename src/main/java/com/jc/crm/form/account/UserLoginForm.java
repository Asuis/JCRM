package com.jc.crm.form.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginForm {
    @NotBlank(message = "用户名为空")
    @Size(min = 1, max = 32)
    @Pattern(regexp = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5@]+$", message = "用户名只含有汉字、数字、字母、下划线不能以下划线开头和结尾")
    private String username;
    @NotBlank(message = "密码为空")
    @Size(min = 8, max = 32, message = "密码长度要求在8-32位之间")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,32}$", message = "密码不能全部由字母和数字组成")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
