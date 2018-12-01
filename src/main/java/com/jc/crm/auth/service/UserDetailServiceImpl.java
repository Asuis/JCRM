package com.jc.crm.auth.service;

import com.jc.crm.auth.JwtUserFactory;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 15988440973
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserMapper userMapper;

    @Autowired
    public UserDetailServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * ps: 此处username指账号名
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =  userMapper.getByEmail(username);
        if (userEntity==null) {
            throw new UsernameNotFoundException(username);
        }
        List<String> roles = userMapper.getRoles(userEntity.uid);
        return JwtUserFactory.create(userEntity, roles);
    }
}
