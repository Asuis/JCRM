package com.jc.crm.service.department.vo;

import com.jc.crm.model.UserEntity;

/**
 * @author asuis
 * @version: UserDepartmentVO.java 18-11-23:上午11:13
 */
public class UserDepartmentVO extends UserEntity {
    private String post;
    private Integer weight;
    private Integer departmentId;
    private String departmentName;

    public Integer getWeight() {
        return weight;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
