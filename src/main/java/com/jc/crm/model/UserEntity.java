package com.jc.crm.model;

import com.jc.crm.form.account.UserUpdateForm;

import java.util.Date;

public class UserEntity {
    public int uid;
    public String username;
    private String avatar;
    private String email;
    private String pass;
    private String salt;
    private Integer contactId;
    private Integer addressId;
    private String phone;
    private int isLock;
    private int eid;
    private Date createTime;
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
