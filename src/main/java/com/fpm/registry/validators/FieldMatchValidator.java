package com.fpm.registry.validators;

import com.fpm.registry.constraints.FieldMatch;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    @SneakyThrows
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var firstObj = BeanUtils.getProperty(value, firstFieldName);
        var secondObj = BeanUtils.getProperty(value, secondFieldName);

        return Objects.equals(firstObj, secondObj);
    }
}
