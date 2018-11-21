package com.jc.crm.validator;

import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.validator.anootation.Enterprise;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author asuis
 * @version: EnterpriseValidator.java 18-11-21:下午12:54
 */
public class EnterpriseValidator implements ConstraintValidator<Enterprise, EnterpriseForm> {
    @Override
    public void initialize(Enterprise constraintAnnotation) {

    }
    @Override
    public boolean isValid(EnterpriseForm value, ConstraintValidatorContext context) {
        return false;
    }
}
