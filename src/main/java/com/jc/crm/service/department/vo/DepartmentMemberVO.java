package com.jc.crm.service.department.vo;

/**
 * @author asuis
 * @version: DepartmentMemberVO.java 18-12-8:下午9:05
 */
public class DepartmentMemberVO {
    private Integer uid;
    private String username;

    @Override
    public String toString() {
        return "DepartmentMemberVO{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
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
}
