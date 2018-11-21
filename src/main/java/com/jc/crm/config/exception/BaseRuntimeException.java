package com.jc.crm.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author asuis
 * @version: BaseRuntimeException.java 18-11-20:下午7:12
 */
public class BaseRuntimeException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(BaseRuntimeException.class);
    public BaseRuntimeException(String message) {
        super(message);
        logger.info(message);
    }
}
