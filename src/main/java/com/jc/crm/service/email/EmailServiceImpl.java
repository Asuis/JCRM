package com.jc.crm.service.email;

import com.jc.crm.config.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author asuis
 * @version: EmailServiceImpl.java 18-12-3:上午10:53
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * @param email   邮件
     * @param captcha 验证码
     * @return code>0 success
     */
    @Override
    public int sendActiveEmail(String email, String captcha) {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        int flag = ResultStatus.FAIL;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage,true,"utf-8");

            helper.setFrom("asuismn@163.com");

            helper.setTo(email);

            helper.setSubject("JCRM注册激活");

            helper.setText(buildEmail(email, captcha),true);

            mailSender.send(mimeMailMessage);
            flag = ResultStatus.SUCCESS;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return flag;
    }
    private String buildEmail(String user,String code) {
        Context context = new Context();
        context.setVariable("user",user);
        context.setVariable("code",code);
        return templateEngine.process("login",context);
    }
}
