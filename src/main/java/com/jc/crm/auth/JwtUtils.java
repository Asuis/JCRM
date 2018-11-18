package com.jc.crm.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.UserEntity;
import com.jc.crm.utils.Base64Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author 15988440973
 */
@Component
public class JwtUtils {
    private static final long EXPIRATIONTIME = 432000000;
    // 5天
    private static final String SECRET = "JC++Key";
    // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";
    // Token前缀
    public static final String HEADER_STRING = "Authorization";
    // 存放Token的Header Key

    private static UserMapper userMapper;

    @Autowired
    public JwtUtils(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // JWT生成方法
    public static String addAuthentication(UserEntity userEntity) {

        if (userEntity.getSalt() == null) throw new RuntimeException("用户验证不应该为空");
        // 生成JWT
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder
                // 用户名写入标题
                .setSubject(userEntity.getUsername())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(Keys.hmacShaKeyFor((SECRET + userEntity.getSalt()).getBytes()));
        return jwtBuilder.compact();
    }

    public static boolean compare(String jwt, String salt) {
        return Jwts.parser()
                .setSigningKey(SECRET + salt)
                .isSigned(jwt);
    }
    public static UserEntity getUsernameFromToken (String token) {
        String msg = token.split("\\.")[1];
        String data = Base64Utils.decode(msg);
        JSONObject jsonObject = JSON.parseObject(data);
        if (!jsonObject.containsKey("sub")) throw new RuntimeException("token格式不正确");
        String account = (String) jsonObject.get("sub");
        UserEntity userEntity = userMapper.getByEmail(account);
        if (!compare(token, userEntity.getSalt())) return null;
        return userEntity;
    }
}
