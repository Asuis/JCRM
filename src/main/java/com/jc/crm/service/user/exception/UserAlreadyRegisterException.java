package com.jc.crm.service.user.exception;

import com.jc.crm.config.exception.BaseRuntimeException;

public class UserAlreadyRegisterException extends BaseRuntimeException {
    public UserAlreadyRegisterException(String s) {
        super(s);
    }
}
