package com.fpm.registry.controllers.view;

import com.fpm.registry.utils.Views;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorViewController implements ErrorController {

    @Override
    public String getErrorPath() {
        return Views.INTERNAL_SERVER_ERROR;
    }
}
