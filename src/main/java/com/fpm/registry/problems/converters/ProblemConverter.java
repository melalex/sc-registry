package com.fpm.registry.problems.converters;

import com.fpm.registry.problems.Problem;

import java.util.Locale;

public interface ProblemConverter<T extends Throwable> {

    Problem convert(T throwable, Locale locale);
}
