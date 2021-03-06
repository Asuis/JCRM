package com.jc.crm.config.logger;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ControllerServiceLog {
    String description() default "";
}
