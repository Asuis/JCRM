package com.jc.crm.service.department.vo;

import com.jc.crm.model.UserEntity;

/**
 * @author asuis
 * @version: UserDepartmentVO.java 18-11-23:上午11:13
 */
public class UserDepartmentVO extends UserEntity {
    private String post;
    private Integer weight;
    private String departmentId;
    private String departmentName;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
