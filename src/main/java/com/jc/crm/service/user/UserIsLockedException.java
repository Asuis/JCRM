package com.jc.crm.service.user;

public class UserIsLockedException extends RuntimeException {
    public UserIsLockedException(String s) {
        super(s);
    }
}
