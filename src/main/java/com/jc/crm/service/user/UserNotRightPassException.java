package com.jc.crm.service.user;

public class UserNotRightPassException extends RuntimeException {
    public UserNotRightPassException(String s) {
        super(s);
    }
}
