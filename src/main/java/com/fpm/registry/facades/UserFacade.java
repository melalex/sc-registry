package com.fpm.registry.facades;

import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.dto.UserDto;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public interface UserFacade {

    UserDto create(SignUpForm dto);

    void login(UserDto user, WebAuthenticationDetails details);

    UserDto getCurrentUser();

    UserDto getCurrentUserOrNull();
}
