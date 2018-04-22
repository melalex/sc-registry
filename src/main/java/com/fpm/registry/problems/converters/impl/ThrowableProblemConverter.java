package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class ThrowableProblemConverter extends AbstractProblemConverter<Throwable> {

    @Override
    protected Class<Throwable> getTarget() {
        return Throwable.class;
    }

    @Override
    public int order() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public Problem convert(Throwable throwable, Locale locale) {
        return null;
    }
}
