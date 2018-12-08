package com.jc.crm.mapper.provider;

import com.jc.crm.form.department.DepartmentUpdateForm;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author asuis
 * @version: DepartmentSqlProvider.java 18-12-8:下午8:16
 */
public class DepartmentSqlProvider {
    private final static Logger logger = LoggerFactory.getLogger(DepartmentSqlProvider.class);
    public String updateDepartment(DepartmentUpdateForm departmentUpdateForm) {
        String sql = new SQL(){{
            UPDATE("department");
            if (departmentUpdateForm.getPid()!=null) {
                SET("department.pid =#{pid}");
            }
            if (departmentUpdateForm.getDescription()!=null) {
                SET("department.description = #{description}");
            }
            if (departmentUpdateForm.getDepartmentName()!=null) {
                SET(" department.department_name = #{departmentName}");
            }
            if (departmentUpdateForm.getStruct()!=null) {
                SET(" struct = #{struct}");
            }
            WHERE("department_id = #{departmentId}");
        }}.toString();
        logger.info("执行sql:" + sql);
        return sql;
    }
}
