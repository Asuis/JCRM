package com.jc.crm.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

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
}
