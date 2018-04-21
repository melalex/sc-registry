package com.fpm.registry.services.impl;

import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.UserRepository;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        return getCurrentUserInternal().orElseThrow(Exceptions.unauthorized());
    }

    @Override
    public User getCurrentUserOrNull() {
        return getCurrentUserInternal().orElse(null);
    }

    private Optional<User> getCurrentUserInternal() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findOneByLogin);
    }
}
