package com.jc.crm.form.department;

import com.jc.crm.model.DepartmentEntity;

import javax.validation.constraints.NotBlank;

/**
 * @author asuis
 * @version: DepartmentCreateForm.java 18-12-8:下午3:06
 */
public class DepartmentCreateForm {
    private Integer pid;
    private String description;
    @NotBlank
    private String departmentName;

    public DepartmentEntity toDepartment(String struct, Integer eid) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName(departmentName);
        departmentEntity.setEid(eid);
        departmentEntity.setDescription(description);
        departmentEntity.setPid(pid);
        departmentEntity.setStruct(struct);
        return departmentEntity;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
