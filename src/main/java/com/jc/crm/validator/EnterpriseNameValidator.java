package com.jc.crm.validator;

import com.jc.crm.service.enterprise.EnterpriseService;
import com.jc.crm.validator.anootation.enterprise.EnterpriseName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author asuis
 * @version: EnterpriseNameValidator.java 18-11-21:下午12:59
 */
public class EnterpriseNameValidator implements ConstraintValidator<EnterpriseName, String> {

    @Autowired
    private EnterpriseService enterpriseService;

    @Override
    public void initialize(EnterpriseName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return enterpriseService.isExist(value) <= 0;
    }
}
