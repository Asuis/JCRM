package com.jc.crm.service.user;
/**
 * 用户接口
 * @author asuis 2018-11-2
 * */
public interface UserService {
    /**
     * @return 是否注册成功 成功>0 200成功 失败<=0 -1失败
     * **/
    int register();
    /**
     * @param username 用户名
     * @param passwd 密码
     * @param eid 企业id
     * @return 登录成功返回 jwt token 失败返回 null
     * */
    String login(String username, String passwd, int eid);
    /**
     * 退出登录
     * 刷新salt值
     * @param token 登录获得的jwt token
     * @return 是否注册成功 成功>0 200成功 失败<=0 -1失败
     * */
    int logout(String token);
    int updateUserDetail();
}
