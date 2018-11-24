package com.jc.crm.service.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.model.UserEntity;
import com.jc.crm.utils.MD5Utils;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author asuis
 */
public class UserFactory {
    private static final String AVATARS[] = {
            "http://res.mengxiangjing.com/dog.jpeg",
            "http://res.mengxiangjing.com/i_love_study_only.jpg",
            "http://res.mengxiangjing.com/i_love_tu.jpg",
            "http://res.mengxiangjing.com/lover.jpeg"
    };
    public static UserEntity create(RegisterForm registerForm) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerForm.getEmail());
        userEntity.setEmail(registerForm.getEmail());
        userEntity.setPass(MD5Utils.encode(registerForm.getPass()));
        userEntity.setAvatar(AVATARS[new Random().nextInt(4)]);
        //生成salt
        userEntity.setSalt(
                new String(
                        Keys.hmacShaKeyFor((userEntity.getPass()+ new Random().nextLong()).getBytes()).getEncoded(), Charset.defaultCharset()));
        return userEntity;
    }
}
