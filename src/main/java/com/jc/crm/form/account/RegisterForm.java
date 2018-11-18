package com.jc.crm.form.account;

public class RegisterForm {
    private String email;
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
