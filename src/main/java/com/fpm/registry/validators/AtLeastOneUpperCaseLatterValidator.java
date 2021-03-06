package com.fpm.registry.validators;


import com.fpm.registry.constraints.AtLeastOneUpperCaseLatter;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneUpperCaseLatterValidator implements ConstraintValidator<AtLeastOneUpperCaseLatter, String> {

    private static final String REG_EXP = "(?=.*[A-Z])";
    private static final Pattern PATTERN = Pattern.compile(REG_EXP);

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return PATTERN.matcher(obj).find();
    }
}
