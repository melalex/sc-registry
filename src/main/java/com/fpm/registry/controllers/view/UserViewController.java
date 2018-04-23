package com.fpm.registry.controllers.view;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.exceptions.UserAlreadyExistsException;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.problems.Problem;
import com.fpm.registry.problems.converters.ProblemConversionService;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@ViewController("/user")
public class UserViewController {

    private static final String CAUGHT_USER_ALREADY_EXISTS_EXCEPTION = "Caught UserAlreadyExistsException: {}";

    private UserFacade userFacade;
    private ProblemConversionService problemConversionService;

    @PostMapping
    public ModelAndView create(@Valid SignUpForm dto) {
        userFacade.create(dto);
        return Views.redirectToIndex();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Problem handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        log.debug(CAUGHT_USER_ALREADY_EXISTS_EXCEPTION, exception.getMessage());

        return problemConversionService.toProblem(exception);
    }
}
