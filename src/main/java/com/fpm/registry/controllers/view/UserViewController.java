package com.fpm.registry.controllers.view;

import static java.util.List.of;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.dto.SignUpDto;
import com.fpm.registry.exceptions.UserAlreadyExistsException;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.utils.Views;
import com.fpm.registry.vo.ProblemVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.Clock;
import java.time.LocalDateTime;
import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@ViewController("/user")
public class UserViewController {

    private static final String CAUGHT_USER_ALREADY_EXISTS_EXCEPTION = "Caught UserAlreadyExistsException: {}";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "errors.user.login.alreadyExists";
    private static final String LOGIN_FIELD = "login";

    private UserFacade userFacade;
    private Clock clock;

    @PostMapping
    public ModelAndView create(@Valid SignUpDto dto) {
        userFacade.create(dto);
        return Views.redirectToIndex();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemVo handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        log.debug(CAUGHT_USER_ALREADY_EXISTS_EXCEPTION, exception.getMessage());

        return ProblemVo.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now(clock))
                .type(UserAlreadyExistsException.class.getSimpleName())
                .detail(exception.getMessage())
                .errors(of(
                        ProblemVo.NestedErrorVo.builder()
                                .field(LOGIN_FIELD)
                                .message(USER_ALREADY_EXISTS_ERROR_MESSAGE)
                                .rejected(exception.getRejected())
                                .build()
                ))
                .build();
    }
}
