package com.jc.crm.service.user.vo;

/**
 * @author asuis
 * @version: UserSimpleVO.java 18-12-9:下午4:10
 */
public class UserSimpleVO {
    private Integer uid;
    private String username;
    private String avatar;

    @Override
    public String toString() {
        return "UserSimpleVO{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
