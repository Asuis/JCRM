package com.jc.crm.form.department;

/**
 * @author asuis
 * @version: DepartmentUpdateForm.java 18-12-8:下午7:59
 */
public class DepartmentUpdateForm extends DepartmentCreateForm {
    private Integer departmentId;
    private String struct;
    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getStruct() {
        return struct;
    }

    public void setStruct(String struct) {
        this.struct = struct;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
