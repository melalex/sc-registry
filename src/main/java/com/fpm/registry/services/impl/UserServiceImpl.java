package com.fpm.registry.services.impl;

import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.UserRepository;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findOneByLogin)
                .orElseThrow(Exceptions.unauthorized());
    }
}
