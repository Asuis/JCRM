package com.jc.crm.service.department.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.jc.crm.model.UserEntity;

/**
 * @author asuis
 * @version: UserDepartmentVO.java 18-11-23:上午11:13
 */
public class UserDepartmentVO extends UserEntity {
    private String post;
    @JSONField(serialize = false)
    private Integer weight;
    private Integer departmentId;
    private String departmentName;

    @Override
    public String toString() {
        return "UserDepartmentVO{" +
                "post='" + post + '\'' +
                ", weight=" + weight +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }

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
