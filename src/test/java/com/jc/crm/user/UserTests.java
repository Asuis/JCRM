package com.jc.crm.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    private static Logger logger = LoggerFactory.getLogger(UserTests.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Test
    public void  isHaveAuth() {
        boolean a = departmentService.isHaveAuth(28, 27);
        logger.info(a+"");
    }
    @Test
    public void loginTests() {
//        userService.login("727443530@qq.com", "Mysql123456");
    }
    @Test
    public void registerTests() {

    }
    @Test
    public void logoutTest() {
//        String user = userService.login("727443530@qq.com", "Mysql123456");
//        userService.logout(user);
    }
    @Test
    public void test() {
        System.out.println(
                Arrays.toString("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiI3Mjc0NDM1MzBAcXEuY29tIiwiZXhwIjoxNTQyNzg3MzA0fQ.WUMgHJIWElxp-Aqar6KUghWQFxZBVfceANrgjtk5gmt3t97r16RnFjdL7vnud842".split("\\."))
        );
    }
    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(4));
        }
    }
}
