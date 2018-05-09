package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.domain.User;
import com.fpm.registry.forms.SignUpForm;
import com.fpm.registry.dto.UserDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

@Facade
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private ExtendedMapper extendedMapper;

    @Override
    @Transactional
    public UserDto create(SignUpForm dto) {
        User toSave = extendedMapper.map(dto, User.class);
        User saved = userService.create(toSave);
        return extendedMapper.map(saved, UserDto.class);
    }

    @Override
    public void login(UserDto user, WebAuthenticationDetails details) {
        userService.login(user.getLogin(), details);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        User current = userService.getCurrentUser();
        return extendedMapper.map(current, UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUserOrNull() {
        User current = userService.getCurrentUserOrNull();
        return extendedMapper.mapNullable(current, UserDto.class);
    }
}
