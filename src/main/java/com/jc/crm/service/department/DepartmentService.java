package com.jc.crm.service.department;

import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.UserEntity;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentService.java 18-11-22:下午9:14
 */
public interface DepartmentService {
    List<UserEntity> getUsersByDepartment(Integer uid);
    List<Integer> getIdsByUser(Integer uid);
    boolean isHaveAuth(Integer uid, Integer cuid);
    /**
     * 获取uid部门及其子部门
     * */
    List<DepartmentEntity> queryDepartment(Integer uid,Integer eid);
    /**
     * 查询企业所有部门
     * */
    List<DepartmentEntity> findAllDepartment(Integer eid);

    int updateDepartment();
}
