package com.fpm.registry.facades;

import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.dto.UserDto;

public interface UserFacade {

    UserDto create(SignUpForm dto);

    UserDto getCurrentUser();

    UserDto getCurrentUserOrNull();
}
