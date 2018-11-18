package com.jc.crm.service.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.model.UserEntity;
import com.jc.crm.utils.MD5Utils;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.Charset;
import java.util.Random;

public class UserFactory {
    public static UserEntity create(RegisterForm registerForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerForm.getEmail());
        userEntity.setEmail(registerForm.getEmail());
        userEntity.setPass(MD5Utils.encode(registerForm.getPass()));
        //生成salt
        userEntity.setSalt(
                new String(
                        Keys.hmacShaKeyFor((userEntity.getPass()+ new Random().nextLong()).getBytes()).getEncoded(), Charset.defaultCharset()));
        return userEntity;
    }
}
