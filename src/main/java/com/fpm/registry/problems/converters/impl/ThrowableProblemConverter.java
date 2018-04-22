package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import com.fpm.registry.services.I18nService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Component
@AllArgsConstructor
public class ThrowableProblemConverter extends AbstractProblemConverter<Throwable> {

    private I18nService i18nService;
    private Clock clock;

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
        return Problem.builder()
                .message(throwable.getMessage())
                .detail(i18nService.getMessage(Exceptions.getErrorCode(getTarget()), locale))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now(clock))
                .type(throwable.getClass().getSimpleName())
                .errors(List.of())
                .build();
    }
}
