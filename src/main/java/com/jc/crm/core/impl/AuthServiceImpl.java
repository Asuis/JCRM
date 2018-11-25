package com.jc.crm.core.impl;

import com.jc.crm.core.AuthService;
import com.jc.crm.mapper.AuthMapper;
import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asuis
 * @version: AuthServiceImpl.java 18-11-25:下午6:45
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final DepartmentMapper departmentMapper;
    @Autowired
    public AuthServiceImpl(AuthMapper authMapper, DepartmentMapper departmentMapper) {
        this.authMapper = authMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public boolean isHaveAuth(UserEntity user, Integer uid) {
        departmentMapper.getDepartmentDetailByUser(user.getUid());
//        departmentMapper.getDepartmentUserByDepartmentIdAndWeight()

        return false;
    }
}
