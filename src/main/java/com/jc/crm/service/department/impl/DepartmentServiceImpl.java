package com.jc.crm.service.department.impl;

import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentServiceImpl.java 18-11-22:下午9:43
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    /**
     * 获得用户组（同组、部门）
     *
     * @param uid
     */
    @Override
    public List<UserEntity> getUsersByDepartment(Integer uid) {
        return null;
    }
}
