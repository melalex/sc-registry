package com.fpm.registry.advices;

import com.fpm.registry.exceptions.UserAlreadyExistsException;
import com.fpm.registry.problems.Problem;
import com.fpm.registry.problems.converters.ProblemConversionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    private static final String CAUGHT_BIND_EXCEPTION = "Caught BindException: {}";
    private static final String CAUGHT_METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "Caught MethodArgumentNotValidException: {}";
    private static final String CAUGHT_USER_ALREADY_EXISTS_EXCEPTION = "Caught UserAlreadyExistsException: {}";

    private ProblemConversionService problemConversionService;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Problem handleConstraintViolationException(ConstraintViolationException exception) {
        log.debug(CAUGHT_CONSTRAINT_VIOLATION_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Problem handleBindException(BindException exception) {
        log.debug(CAUGHT_BIND_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Problem handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.debug(CAUGHT_METHOD_ARGUMENT_NOT_VALID_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Problem handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        log.debug(CAUGHT_USER_ALREADY_EXISTS_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }
}
