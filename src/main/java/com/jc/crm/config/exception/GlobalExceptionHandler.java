package com.jc.crm.config.exception;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Object eh(Exception e) {
        e.printStackTrace();
        logger.info(TimeUtils.getNowTime().toLocaleString() + " 未知异常: ", e);
        return Result.fail(ResultStatus.FAIL, "未知异常");
    }
}
