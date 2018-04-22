package com.fpm.registry.advices;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.exceptions.ResourceNotFoundException;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@ControllerAdvice(annotations = ViewController.class)
public class ViewControllerAdvice {

    private static final String HANDLED_NOT_FOUND_EXCEPTION = "Caught ResourceNotFoundException: {}";
    private static final String HANDLED_UNEXPECTED_EXCEPTION = "Caught Throwable: {}";

    private static final String USER_ATTRIBUTE = "user";

    private UserFacade userFacade;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFound(ResourceNotFoundException exception) {
        log.debug(HANDLED_NOT_FOUND_EXCEPTION, exception.getMessage());

        return Views.from(Views.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleUnexpectedException(Throwable exception) {
        log.error(HANDLED_UNEXPECTED_EXCEPTION, exception);

        return Views.from(Views.INTERNAL_SERVER_ERROR);
    }

    @ModelAttribute
    public void addCurrentUser(Model model) {
        model.addAttribute(USER_ATTRIBUTE, userFacade.getCurrentUserOrNull());
    }
}
