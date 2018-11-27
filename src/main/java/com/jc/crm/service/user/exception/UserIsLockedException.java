package com.jc.crm.service.user.exception;

public class UserIsLockedException extends RuntimeException {
    public UserIsLockedException(String s) {
        super(s);
    }
}
