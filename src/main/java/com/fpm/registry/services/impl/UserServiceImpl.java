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

    private static final String GET_BY_LOGIN_MESSAGE = "Fetching user by login";
    private static final String GET_CURRENT_MESSAGE = "Fetching current user";
    private static final String CREATE_USER_MESSAGE = "Creating new User with login [{}]";

    private UserRepository userRepository;

    @Override
    public User create(User user) {
        log.info(CREATE_USER_MESSAGE, user.getLogin());

        user.setId(null);

        getByLogin(user.getLogin())
                .ifPresent(Exceptions.userAlreadyExists());

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        return getCurrentUserInternal().orElseThrow(Exceptions.unauthorized());
    }

    @Override
    public User getCurrentUserOrNull() {
        return getCurrentUserInternal().orElse(null);
    }

    @Override
    public Optional<User> getByLogin(String username) {
        log.trace(GET_BY_LOGIN_MESSAGE);
        return userRepository.findOneByLogin(username);
    }

    private Optional<User> getCurrentUserInternal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.trace(GET_CURRENT_MESSAGE);

        return Optional.ofNullable(authentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findOneByLogin);
    }
}
