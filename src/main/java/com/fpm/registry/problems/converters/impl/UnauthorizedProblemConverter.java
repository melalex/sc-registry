package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.exceptions.UnauthorizedException;
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
public class UnauthorizedProblemConverter extends AbstractProblemConverter<UnauthorizedException> {

    private I18nService i18nService;
    private Clock clock;

    @Override
    protected Class<UnauthorizedException> getTarget() {
        return UnauthorizedException.class;
    }

    @Override
    public Problem convert(UnauthorizedException throwable, Locale locale) {
        return Problem.builder()
                .message(throwable.getMessage())
                .detail(i18nService.getMessage(Exceptions.getErrorCode(getTarget()), locale))
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now(clock))
                .type(throwable.getClass().getSimpleName())
                .errors(List.of())
                .build();
    }
}
