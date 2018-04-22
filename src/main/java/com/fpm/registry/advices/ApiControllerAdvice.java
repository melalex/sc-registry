package com.fpm.registry.advices;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.exceptions.ResourceNotFoundException;
import com.fpm.registry.exceptions.UnauthorizedException;
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
@AllArgsConstructor
@ControllerAdvice(annotations = ApiController.class)
public class ApiControllerAdvice {

    private static final String HANDLED_NOT_FOUND_EXCEPTION = "Caught ResourceNotFoundException: {}";
    private static final String CAUGHT_CONSTRAINT_VIOLATION_EXCEPTION = "Caught ConstraintViolationException: {}";
    private static final String HANDLED_UNEXPECTED_EXCEPTION = "Caught Throwable: {}";
    private static final String HANDLED_UNAUTHORIZED_EXCEPTION = "Caught UnauthorizedException: {}";

    private ProblemConversionService problemConversionService;

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Problem handleNotFound(ResourceNotFoundException exception) {
        log.debug(HANDLED_NOT_FOUND_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Problem handleConstraintViolationException(ConstraintViolationException exception) {
        log.debug(CAUGHT_CONSTRAINT_VIOLATION_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Problem handleUnauthorized(UnauthorizedException exception) {
        log.debug(HANDLED_UNAUTHORIZED_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Problem handleUnexpectedException(Throwable exception) {
        log.error(HANDLED_UNEXPECTED_EXCEPTION, exception);

        return problemConversionService.toProblem(exception);
    }
}
