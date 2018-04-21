package com.fpm.registry.controllers.view;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.facades.UserFacade;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ViewController("/user")
public class UserViewController {

    private UserFacade userFacade;


}
