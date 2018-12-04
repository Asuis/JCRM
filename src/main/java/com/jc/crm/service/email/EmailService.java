package com.jc.crm.service.email;

/**
 * @author asuis
 * @version: EmailService.java 18-12-3:上午10:53
 */
public interface EmailService {
    /**
     * @param email 邮件
     * @param captcha 验证码
     * @return code>0 success
     * */
    int sendActiveEmail(String email, String captcha);
}
