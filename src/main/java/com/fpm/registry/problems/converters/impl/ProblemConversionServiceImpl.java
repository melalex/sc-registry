package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import com.fpm.registry.problems.converters.ProblemConversionService;
import com.fpm.registry.problems.converters.ProblemConverter;
import com.fpm.registry.utils.Maps;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Map;

@AllArgsConstructor
public class ProblemConversionServiceImpl implements ProblemConversionService {

    private Map<Class<? extends Throwable>, ProblemConverter<? extends Throwable>> conversionMap;

    @Override
    public <T extends Throwable> Problem toProblem(T throwable) {

        @SuppressWarnings("unchecked")
        var converter = (ProblemConverter<T>) Maps.getOrThrow(conversionMap, throwable.getClass());

        return converter.convert(throwable, LocaleContextHolder.getLocale());
    }
}
