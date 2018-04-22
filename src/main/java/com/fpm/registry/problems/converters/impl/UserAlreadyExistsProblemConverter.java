package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.exceptions.UserAlreadyExistsException;
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
public class UserAlreadyExistsProblemConverter extends AbstractProblemConverter<UserAlreadyExistsException> {

    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "errors.user.login.alreadyExists";
    private static final String LOGIN_FIELD = "login";

    private Clock clock;
    private I18nService i18nService;

    @Override
    protected Class<UserAlreadyExistsException> getTarget() {
        return UserAlreadyExistsException.class;
    }

    @Override
    public Problem convert(UserAlreadyExistsException throwable, Locale locale) {
        return Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(clock))
                .type(UserAlreadyExistsException.class.getSimpleName())
                .detail(i18nService.getMessage(Exceptions.getErrorCode(throwable), locale))
                .message(throwable.getMessage())
                .errors(getNestedErrors(throwable, locale))
                .build();
    }

    private List<Problem.NestedError> getNestedErrors(UserAlreadyExistsException throwable, Locale locale) {
        var error = Problem.NestedError.builder()
                .field(LOGIN_FIELD)
                .message(i18nService.getMessage(USER_ALREADY_EXISTS_ERROR_MESSAGE, locale))
                .rejected(throwable.getRejected())
                .build();

        return List.of(error);
    }
}
