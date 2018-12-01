package com.jc.crm.service.department.impl;

import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentServiceImpl.java 18-11-22:下午9:43
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 获得用户组（同组、部门）
     *
     * @param uid 用户id
     */
    @Override
    public List<UserEntity> getUsersByDepartment(Integer uid) {
        return null;
    }

    @Override
    public boolean isHaveAuth(Integer uid, Integer cuid) {
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(uid);
        return departmentMapper.isHaveAuth(departmentVO.getWeight(),departmentVO.getDepartmentId(),departmentVO.getEid(),cuid) > 0;
    }
}
