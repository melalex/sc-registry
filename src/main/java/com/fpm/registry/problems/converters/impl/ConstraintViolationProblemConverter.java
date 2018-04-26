package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.problems.Problem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;

@Component
@AllArgsConstructor
public class ConstraintViolationProblemConverter extends AbstractProblemConverter<ConstraintViolationException> {

    private Clock clock;

    @Override
    protected Class<ConstraintViolationException> getTarget() {
        return ConstraintViolationException.class;
    }

    @Override
    public Problem convert(ConstraintViolationException throwable, Locale locale) {
        return Problem.builder()
                .timestamp(LocalDateTime.now(clock))
                .type(throwable.getClass().getSimpleName())
                .detail(throwable.getLocalizedMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .errors(toErrors(throwable))
                .build();
    }

    private List<Problem.NestedError> toErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(toNestedError())
                .collect(Collectors.toList());
    }

    private Function<ConstraintViolation, Problem.NestedError> toNestedError() {
        return v -> Problem.NestedError.builder()
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
    }}
