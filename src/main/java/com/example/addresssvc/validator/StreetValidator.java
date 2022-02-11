package com.example.addresssvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class StreetValidator implements ConstraintValidator<StreetValidation, String> {
    public boolean isValid(String street, ConstraintValidatorContext cxt) {
        return null != street || !street.trim().isEmpty();
    }
}
