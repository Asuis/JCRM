package com.jc.crm.service.user.vo;

/**
 * @author asuis
 * @version: UserLoginVo.java 18-11-30:下午1:14
 */
public class UserLoginVO {
    private String username;
    private String token;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
