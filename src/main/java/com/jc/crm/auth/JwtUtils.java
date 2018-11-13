package com.jc.crm.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 15988440973
 */
@Component
public class JwtUtils {
    private static final long EXPIRATIONTIME = 432000000;
    // 5天
    private static final String SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
    // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";
    // Token前缀
    public static final String HEADER_STRING = "Authorization";
    // 存放Token的Header Key

    // JWT生成方法
    public static String addAuthentication(String username) {

        // 生成JWT
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder
                // 用户名写入标题
                .setSubject(username)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET);
        return jwtBuilder.compact();
    }

    public static boolean compare(String jwt) {
        try {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String username = claims.getSubject();
        }catch (Exception e) {
            return false;
        }
        return true;
    }
    public static String getUsernameFromToken (String token) {
        String username = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            username = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
}
