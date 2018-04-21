package com.fpm.registry.advices;

import com.fpm.registry.annotations.ApiController;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(annotations = ApiController.class)
public class ApiExceptionHandler {

}
