package com.jc.crm.service.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author asuis
 */
public class QiniuUploadToken {
    private String token;
    private String key;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
