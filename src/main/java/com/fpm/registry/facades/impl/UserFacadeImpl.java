package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.domain.User;
import com.fpm.registry.dto.SignUpDto;
import com.fpm.registry.dto.UserDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.UserFacade;
import com.fpm.registry.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private ExtendedMapper extendedMapper;

    @Override
    @Transactional
    public UserDto create(SignUpDto dto) {
        var toSave = extendedMapper.map(dto, User.class);
        var saved = userService.create(toSave);
        return extendedMapper.map(saved, UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUser() {
        var current = userService.getCurrentUser();
        return extendedMapper.map(current, UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getCurrentUserOrNull() {
        var current = userService.getCurrentUserOrNull();
        return extendedMapper.map(current, UserDto.class);
    }
}
