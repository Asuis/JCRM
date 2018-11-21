package com.jc.crm.validator.anootation;

import com.jc.crm.validator.AddressValidator;
import com.jc.crm.validator.EnterpriseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author asuis
 * @version: Enterprise.java 18-11-21:下午12:55
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnterpriseValidator.class)
public @interface Enterprise {
    String message() default"地址格式问题";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
