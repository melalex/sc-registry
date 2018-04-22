package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.exceptions.UnauthorizedException;
import com.fpm.registry.problems.Problem;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UnauthorizedProblemConverter extends AbstractProblemConverter<UnauthorizedException> {

    @Override
    protected Class<UnauthorizedException> getTarget() {
        return UnauthorizedException.class;
    }

    @Override
    public Problem convert(UnauthorizedException throwable, Locale locale) {
        return null;
    }
}
