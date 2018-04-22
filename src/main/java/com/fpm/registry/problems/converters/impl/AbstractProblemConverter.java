package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.converters.ProblemConverter;

public abstract class AbstractProblemConverter<T extends Throwable> implements ProblemConverter<T> {

    static final int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;
    static final int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    @Override
    public boolean supports(Throwable throwable) {
        return getTarget().isAssignableFrom(throwable.getClass());
    }

    @Override
    public int order() {
        return HIGHEST_PRECEDENCE;
    }

    protected abstract Class<T> getTarget();
}
