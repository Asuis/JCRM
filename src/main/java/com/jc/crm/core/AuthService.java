package com.jc.crm.core;

import com.jc.crm.model.UserEntity;

/**
 * @author asuis
 * @version: AuthService.java 18-11-25:下午6:45
 */
public interface AuthService {
    boolean isHaveAuth(UserEntity user, Integer uid);
}
