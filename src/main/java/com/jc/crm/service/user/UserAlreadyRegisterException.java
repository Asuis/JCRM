package com.jc.crm.service.user;

public class UserAlreadyRegisterException extends RuntimeException {
    public UserAlreadyRegisterException(String s) {
        super(s);
    }
}
