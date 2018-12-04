package com.jc.crm.service.user.vo;

import com.jc.crm.model.TagEntity;

import java.util.List;

/**
 * @author asuis
 * @version: UserDetailVo.java 18-12-2:下午10:09
 */
public class UserDetailVO {
    private String name;
    private String avatar;
    private String userid;
    private String email;
    private String signature;
    private String title;
    private String group;
    private List<TagEntity> tags;
    private Integer notifyCount;
    private Integer unreadCount;
    private GeographicVO geographic;

    public void setNotifyCount(Integer notifyCount) {
        this.notifyCount = notifyCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public GeographicVO getGeographic() {
        return geographic;
    }

    public void setGeographic(GeographicVO geographic) {
        this.geographic = geographic;
    }

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    @Override
    public String toString() {
        return "UserDetailVO{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userid='" + userid + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", title='" + title + '\'' +
                ", group='" + group + '\'' +
                ", tags=" + tags +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}
