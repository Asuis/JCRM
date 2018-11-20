package com.jc.crm;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.service.user.JwtTokenUtils;
import com.jc.crm.service.user.UserService;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {

    private Logger logger = LoggerFactory.getLogger(CrmApplicationTests.class);
    @Autowired
    private UserService userService;
//    @Autowired
//    private UserMapper userMapper;
//    @Test
//    public void contextLoads() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("asuis");
//        userEntity.setEmail("727443530@qq.com");
//        userMapper.insert(userEntity);
//        List<UserEntity> users = userMapper.getUserList();
//        System.out.print(users);
//    }
    @Test
    public void jwtTest() {
        String token = JwtTokenUtils.addAuthentication("Asuis","1","","123");

        logger.debug(token);
        logger.debug("username:",JwtTokenUtils.getUsernameFromToken(token));
    }
    @Test
    @Rollback
    public void registerTest() {
        RegisterForm form = new RegisterForm();
        form.setEmail("727443530@qq.com");
        form.setPass("123456aA");
        form.setVerifyCode("hello hello");
        userService.register(form);
    }

}
