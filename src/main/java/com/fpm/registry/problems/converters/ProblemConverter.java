package com.fpm.registry.problems.converters;

import com.fpm.registry.problems.Problem;

import java.util.Locale;

public interface ProblemConverter<T extends Throwable> {

    boolean supports(Throwable throwable);

    int order();

    Problem convert(T throwable, Locale locale);
}
