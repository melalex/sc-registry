package com.fpm.registry.advices;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class StringTrimmerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        var trimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmer);
    }
}
