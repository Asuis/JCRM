package com.jc.crm.form.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author asuis
 */
public class RegisterForm {
    @Email(message = "message.email不是邮箱格式")
    private String email;
    @NotBlank(message = "密码为空")
    @Size(min = 8, max = 32, message = "密码长度要求在8-32位之间")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,32}$", message = "密码不能全部由字母和数字组成")
    private String pass;
    private String verifyCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
