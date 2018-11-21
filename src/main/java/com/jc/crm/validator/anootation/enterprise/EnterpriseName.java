package com.jc.crm.validator.anootation.enterprise;

import com.jc.crm.validator.AddressValidator;
import com.jc.crm.validator.EnterpriseNameValidator;
import com.jc.crm.validator.anootation.Enterprise;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author asuis
 * @version: EnterpriseName.java 18-11-21:下午12:59
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnterpriseNameValidator.class)
public @interface EnterpriseName {
    String message() default"企业名已存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
