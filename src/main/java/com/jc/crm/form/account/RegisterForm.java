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
    @NotBlank(message = "mail不能为空")
    private String mail;
    @NotBlank(message = "密码为空")
    @Size(min = 8, max = 32, message = "密码长度要求在8-32位之间")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,32}$", message = "密码不能全部由字母和数字组成")
    private String pass;
    private String confirm;
    private String prefix;
    private String mobile;
    private String captcha;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setcaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
