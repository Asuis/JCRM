package com.jc.crm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.jc.crm.form.account.UserUpdateForm;

import java.util.Date;

/**
 * @author asuis
 */
public class UserEntity {
    public int uid;
    public String username;
    private String avatar;
    private String email;
    @JSONField(serialize = false)
    private String pass;
    @JSONField(serialize = false)
    private String salt;
    private Integer contactId;
    private Integer addressId;
    private String phone;
    private int isLock;
    private Integer eid;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date ctime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date utime;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserEntity() {
    }

    public UserEntity(UserUpdateForm form, Integer addressId, Integer contactId) {
        this.setUsername(form.getUsername());
        this.setAddressId(addressId);
        this.setContactId(contactId);
    }

    public int getEid() {
        return eid;
    }
    public void setEid(int eid) {
        this.eid = eid;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", salt='" + salt + '\'' +
                ", contactId=" + contactId +
                ", addressId=" + addressId +
                ", phone='" + phone + '\'' +
                ", isLock=" + isLock +
                ", eid=" + eid +
                ", createTime=" + ctime +
                ", updateTime=" + utime +
                '}';
    }
}
