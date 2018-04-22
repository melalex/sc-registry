package com.fpm.registry.advices;

import com.fpm.registry.problems.Problem;
import com.fpm.registry.problems.converters.ProblemConversionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class CommonControllerAdvice {

    private static final String CAUGHT_CONSTRAINT_VIOLATION_EXCEPTION = "Caught ConstraintViolationException: {}";

    private ProblemConversionService problemConversionService;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Problem handleConstraintViolationException(ConstraintViolationException exception) {
        log.debug(CAUGHT_CONSTRAINT_VIOLATION_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }
}
