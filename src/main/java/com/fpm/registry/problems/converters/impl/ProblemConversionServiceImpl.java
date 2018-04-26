package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import com.fpm.registry.problems.converters.ProblemConversionService;
import com.fpm.registry.problems.converters.ProblemConverter;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProblemConversionServiceImpl implements ProblemConversionService {

    private static final String ERROR_MESSAGE = "There is no no suitable ProblemConverter";

    private List<ProblemConverter> problemConverters;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Throwable> Problem toProblem(T throwable) {
        return problemConverters.stream()
                .sorted(Comparator.comparingInt(ProblemConverter::order))
                .filter(c -> c.supports(throwable))
                .findAny()
                .orElseThrow(Exceptions.illegalState(ERROR_MESSAGE))
                .convert(throwable, LocaleContextHolder.getLocale());
    }
}
