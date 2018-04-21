package com.fpm.registry.facades;

import com.fpm.registry.dto.UserDto;

public interface UserFacade {

    UserDto getCurrentUser();

    UserDto getCurrentUserOrNull();
}
