package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import com.fpm.registry.services.I18nService;
import com.fpm.registry.utils.Messages;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;

@Component
@AllArgsConstructor
public class BindProblemConverter extends AbstractProblemConverter<BindException> {

    private Clock clock;
    private I18nService i18nService;

    @Override
    protected Class<BindException> getTarget() {
        return BindException.class;
    }

    @Override
    public Problem convert(BindException throwable, Locale locale) {
        return Problem.builder()
                .timestamp(LocalDateTime.now(clock))
                .type(throwable.getClass().getSimpleName())
                .detail(i18nService.getMessage(Messages.VALIDATION_ERROR, locale))
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(toErrors(throwable))
                .build();
    }

    private List<Problem.NestedError> toErrors(BindException exception) {
        return exception.getAllErrors().stream()
                .map(o -> o.unwrap(ConstraintViolation.class))
                .map(this::toNestedError)
                .collect(Collectors.toList());
    }

    private Problem.NestedError toNestedError(ConstraintViolation v) {
        return Problem.NestedError.builder()
                .field(getField(v))
                .rejected(String.valueOf(v.getInvalidValue()))
                .message(v.getMessage())
                .build();
    }

    private String getField(ConstraintViolation violation) {
        return StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                .filter(n -> ElementKind.PROPERTY.equals(n.getKind()))
                .map(Path.Node::getName)
                .filter(Objects::nonNull)
                .reduce((f, s) -> s)
                .orElse(null);
    }
}
