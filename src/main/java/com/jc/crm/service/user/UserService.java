package com.jc.crm.service.user;

import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.account.UserUpdateForm;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.user.exception.UserAlreadyRegisterException;
import com.jc.crm.service.user.exception.UserIsLockedException;
import com.jc.crm.service.user.exception.UserNotFoundException;
import com.jc.crm.service.user.exception.UserNotRightPassException;
import com.jc.crm.service.user.vo.UserDetailVO;

import java.util.List;

/**
 * 用户接口
 * @author asuis 2018-11-2
 * */
public interface UserService {
    /**
     * @param registerForm 注册表单
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
    /**
     * @param uid 用户id
     * @param updateForm 更新用户表单
     * @return code > 0 成功
     * */
    int updateUserDetail(UserUpdateForm updateForm, int uid);
    /**
     * @param uid 用户id
     * @param verifyCode 验证码
     * @return code >0成功
     * */
    int active(int uid, String verifyCode);
    /**
     * 获得权限
     * @param uid 用户id
     * @return 用户权限
     * */
    List<String> getRoles(Integer uid);

    UserDetailVO getCurrentUserDetails(UserEntity user);
}
