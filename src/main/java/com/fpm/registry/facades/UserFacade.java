package com.fpm.registry.facades;

import com.fpm.registry.dto.SignUpDto;
import com.fpm.registry.dto.UserDto;

public interface UserFacade {

    UserDto create(SignUpDto dto);

    UserDto getCurrentUser();

    UserDto getCurrentUserOrNull();
}
