package com.jc.crm.validator.anootation;

import com.jc.crm.validator.AddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface Address {
    String message() default"地址格式问题";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
