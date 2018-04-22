package com.fpm.registry.problems.converters;

import com.fpm.registry.problems.Problem;

public interface ProblemConversionService {

    <T extends Throwable> Problem toProblem(T throwable);
}
