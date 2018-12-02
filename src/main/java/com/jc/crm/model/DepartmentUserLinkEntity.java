package com.jc.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 部门与用户链接表(department_user_link)实体类
 * @author currysss 2018-12-1
 * */
public class DepartmentUserLinkEntity {

    private Integer departmentUserId;

    private Integer userId;

    private Integer departmentId;

    private Integer isActived;

    private Integer weight;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ctime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date utime;

    public Integer getDepartmentUserId() {
        return departmentUserId;
    }

    public void setDepartmentUserId(Integer departmentUserId) {
        this.departmentUserId = departmentUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
}
