package com.jc.crm.service.user.impl;

import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.user.UserManagerService;
import com.jc.crm.service.user.UserService;
import com.jc.crm.service.user.vo.UserDetailVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asuis
 * @version: UserManagerServiceImpl.java 18-12-7:下午6:47
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    private final UserService userService;

    private final UserMapper userMapper;

    @Autowired
    public UserManagerServiceImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public int registerFromList() {
        return 0;
    }

    @Override
    public int deleteFromList(List<Integer> userList) {
        return 0;
    }

    @Override
    public String exportUserList(int eid) {
        return null;
    }

    @Override
    public UserDetailVO getDetailsForUser(Integer uid) {
        UserEntity userEntity = userMapper.getByUid(uid);
        return userService.getCurrentUserDetails(userEntity);
    }
}
