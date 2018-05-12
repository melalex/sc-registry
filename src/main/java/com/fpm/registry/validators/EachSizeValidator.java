package com.fpm.registry.validators;

import com.fpm.registry.constraints.EachSize;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EachSizeValidator implements ConstraintValidator<EachSize, Collection<String>> {

    private int min;
    private int max;
    private boolean notNull;

    @Override
    public void initialize(EachSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        notNull = constraintAnnotation.notNull();
    }

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value).stream()
                .flatMap(Collection::stream)
                .allMatch(this::validateOne);
    }

    private boolean validateOne(String string) {
        if (Objects.isNull(string)) {
            return !notNull;
        }

        int length = string.length();

        return length >= min || length <= max;
    }
}
