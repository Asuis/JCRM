package com.jc.crm.config.logger;

import com.jc.crm.config.Result;
import com.jc.crm.utils.TimeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);



    @Pointcut(value = "@annotation(log)")
    public void serviceCell(SystemServiceLog log){}

    @Pointcut(value = "@annotation(log)")
    public void controllerCell(ControllerServiceLog log) {}

    private StringBuilder showParams(JoinPoint joinPoint) {
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            params.append("[");
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(joinPoint.getArgs()[i]);
                params.append(",");
            }
            params.deleteCharAt(params.length()-1);
            params.append("]");
        }
        return params;
    }

    @Before(value = "serviceCell(log)", argNames = "joinPoint,log")
    public void beforeLog(JoinPoint joinPoint, SystemServiceLog log) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("=========================");
        logger.info(TimeUtils.getNowTime().toLocaleString() + "执行" + methodName + "方法");
        logger.info("执行前参数:" + showParams(joinPoint));
    }
    @AfterReturning(value = "serviceCell(log))", returning = "ret", argNames = "joinPoint,ret,log")
    public void afterLog(JoinPoint joinPoint, Object ret, SystemServiceLog log) {
        String methodName = joinPoint.getSignature().getName();

        logger.info("参数:" + showParams(joinPoint));
        logger.info("返回值:"+ log.description() + ret.toString());
        logger.info(TimeUtils.getNowTime().toLocaleString() + "执行" + methodName + "方法结束");
        logger.info("=========================");
    }
    @Before(value = "controllerCell(log)", argNames = "joinPoint,log")
    public void beforeControllerLog(JoinPoint joinPoint, ControllerServiceLog log) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("========================= =========================");
        logger.info("request请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
    }
    @AfterReturning(value = "controllerCell(log)", argNames = "joinPoint,log, ret", returning = "ret")
    public void afterControllerLog(JoinPoint joinPoint, ControllerServiceLog log, Object ret) {
        logger.info("RESPONSE : " + ret);
    }
}
