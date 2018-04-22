package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;
import javax.validation.ConstraintViolationException;

@Component
@AllArgsConstructor
public class ConstraintViolationProblemConverter extends AbstractProblemConverter<ConstraintViolationException> {

    @Override
    protected Class<ConstraintViolationException> getTarget() {
        return ConstraintViolationException.class;
    }

    @Override
    public Problem convert(ConstraintViolationException throwable, Locale locale) {
        return null;
    }
}
