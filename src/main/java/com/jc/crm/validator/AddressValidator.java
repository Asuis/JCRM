package com.jc.crm.validator;

import com.jc.crm.model.AddressEntity;
import com.jc.crm.validator.anootation.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, AddressEntity> {
    @Override
    public void initialize(Address constraintAnnotation) {

    }

    @Override
    public boolean isValid(AddressEntity value, ConstraintValidatorContext context) {
        return false;
    }
}
