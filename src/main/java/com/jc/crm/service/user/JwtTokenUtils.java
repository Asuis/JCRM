package com.jc.crm.service.user;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;

public class JwtTokenUtils {
    /** 5天 **/
    private static final long EXPIRATIONTIME = 432000000;
    /** JWT密码 **/
    private static final String SECRET = "P+ssw02d1234567891011121314151617181920212223252526272829303132343536373839404142434446474849505152535455565758596061626364656667787970";
    /** Token前缀 **/
    private static final String TOKEN_PREFIX = "Bearer";
    /** 存放Token的Header Key **/
    public static final String HEADER_STRING = "Authorization";

    /** JWT生成方法 **/
    public static String addAuthentication(String username,String userId,String avatarUrl,String salt) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("avatarUrl", avatarUrl);
        map.put("salt", salt);
        // 生成JWT
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder
                // 用户名写入标题
                .setSubject(userId)
                .setClaims(map)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET);
        return jwtBuilder.compact();
    }

    public static boolean compare(String jwt, String salt) {
        try {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String username = claims.getSubject();
            if (!salt.equals(claims.get("salt"))){
                return false;
            }
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
    public static String refreshToken(String jwt, String salt){
        String username = null;
        String userId = null;
        String avatarUrl = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
                    .getBody();
            username = claims.getSubject();
            userId = (String) claims.get("userId");
            avatarUrl = (String) claims.get("avatarUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return addAuthentication(username,userId,avatarUrl, salt);
    }
}
