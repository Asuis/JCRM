package com.jc.crm.auth;

import com.jc.crm.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15988440973
 * 试着尝试用java8流语法
 */
public class JwtUserFactory {
    private JwtUserFactory() {}
    public static JwtUserDetails create(UserEntity user, List<String> auths) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String auth:auths) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        boolean accountNonExpired = false;
        boolean enabled = false;
        if (!"0".equals(user.getIsLock())) {
            accountNonExpired = true;
        }
        if (!"0".equals(user.getIsLock())) {
            accountNonExpired = true;
        }
        return new JwtUserDetails(
                user.getUid(),
                user.getPass(),
                user.getUsername(),
                grantedAuthorities,
                accountNonExpired,
                accountNonExpired,
                true,
                enabled
        );
    }
}
