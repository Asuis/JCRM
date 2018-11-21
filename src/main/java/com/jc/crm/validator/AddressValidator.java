package com.jc.crm.validator;

import com.jc.crm.form.AddressForm;
import com.jc.crm.model.AddressEntity;
import com.jc.crm.validator.anootation.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author asuis
 */
public class AddressValidator implements ConstraintValidator<Address, AddressForm> {
    @Override
    public void initialize(Address constraintAnnotation) {

    }

    @Override
    public boolean isValid(AddressForm value, ConstraintValidatorContext context) {
        return false;
    }
}
