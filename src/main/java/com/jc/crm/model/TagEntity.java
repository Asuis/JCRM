package com.jc.crm.model;

/**
 * 标签表(tag)实体类
 * @author currysss 2018-11-16
 * */
public class TagEntity {

    private Integer tagId;

    private String tagName;

    private String description;

    private Integer pid;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
