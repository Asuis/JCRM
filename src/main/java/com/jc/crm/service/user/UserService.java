package com.jc.crm.service.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.account.UserUpdateForm;

/**
 * 用户接口
 * @author asuis 2018-11-2
 * */
public interface UserService {
    /**
     * @return 是否注册成功 成功>0 200成功 失败<=0 -1失败
     * **/
    int register(RegisterForm registerForm) throws UserAlreadyRegisterException;
    /**
     * @param username 用户名
     * @param pass 密码
     * @return 登录成功返回 jwt token 失败返回 null
     * */
    String login(String username, String pass) throws UserNotFoundException, UserNotRightPassException, UserIsLockedException;
    /**
     * 退出登录
     * 刷新salt值
     * @param token 登录获得的jwt token
     * @return 是否注册成功 成功>0 200成功 失败<=0 -1失败
     * */
    int logout(String token);

    int updateUserDetail(UserUpdateForm updateForm, int uid);

    int active(int uid, String verifyCode);
}
