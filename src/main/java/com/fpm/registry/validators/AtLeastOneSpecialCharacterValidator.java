package com.fpm.registry.validators;


import com.fpm.registry.constraints.AtLeastOneSpecialCharacter;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastOneSpecialCharacterValidator implements ConstraintValidator<AtLeastOneSpecialCharacter, String> {

    private static final String REG_EXP = "(?=.*[@#$%^&+=])";
    private static final Pattern PATTERN = Pattern.compile(REG_EXP);

    @Override
    public void initialize(AtLeastOneSpecialCharacter constraintAnnotation) {

    }

    @Override
    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return PATTERN.matcher(obj).matches();
    }
}
