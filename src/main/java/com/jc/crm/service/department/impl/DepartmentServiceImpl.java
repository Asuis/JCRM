package com.jc.crm.service.department.impl;

import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.model.DepartmentEntity;
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
    public List<Integer> getIdsByUser(Integer uid) {
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(uid);
        if(departmentVO==null) {
            return null;
        }
        return departmentMapper.getDepartmentUserByDepartmentId(departmentVO.getDepartmentId(), departmentVO.getEid());
    }

    @Override
    public boolean isHaveAuth(Integer uid, Integer cuid) {
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(uid);
        if(departmentVO==null) {
            return false;
        }
        return departmentMapper.isHaveAuth(departmentVO.getWeight(),departmentVO.getDepartmentId(),departmentVO.getEid(),cuid) > 0;
    }

    /**
     * 获取uid部门及其子部门
     *
     * @param uid
     * @param eid
     */
    @Override
    public List<DepartmentEntity> queryDepartment(Integer uid, Integer eid) {
        //todo
        return null;
    }

    /**
     * 查询企业所有部门
     *
     * @param eid
     */
    @Override
    public List<DepartmentEntity> findAllDepartment(Integer eid) {
        return null;
    }

    @Override
    public int updateDepartment() {
        return 0;
    }
}
