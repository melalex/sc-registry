package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.exceptions.ResourceNotFoundException;
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
public class NotFoundProblemConverter extends AbstractProblemConverter<ResourceNotFoundException> {

    private static final String ERROR_MESSAGE_CODE = "errors.entity.notFound";

    private I18nService i18nService;
    private Clock clock;

    @Override
    protected Class<ResourceNotFoundException> getTarget() {
        return ResourceNotFoundException.class;
    }

    @Override
    public Problem convert(ResourceNotFoundException throwable, Locale locale) {
        return Problem.builder()
                .message(throwable.getMessage())
                .detail(i18nService.getMessage(Exceptions.getErrorCode(throwable), locale))
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now(clock))
                .type(throwable.getClass().getSimpleName())
                .errors(getNestedErrors(throwable, locale))
                .build();
    }


    private List<Problem.NestedError> getNestedErrors(ResourceNotFoundException throwable, Locale locale) {
        String entityName = throwable.getEntityName();
        String fieldName = throwable.getFieldName();
        String fieldValue = throwable.getFieldValue();
        Problem.NestedError error = Problem.NestedError.builder()
                .field(fieldName)
                .message(i18nService.getMessage(ERROR_MESSAGE_CODE, locale, entityName, fieldName, fieldValue))
                .rejected(fieldValue)
                .build();

        return List.of(error);
    }
}
