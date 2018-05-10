package com.fpm.registry.services.impl;

import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.UserRepository;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String GET_BY_LOGIN_MESSAGE = "Fetching user by login [{}]";
    private static final String GET_CURRENT_MESSAGE = "Fetching current user [{}]";
    private static final String CREATE_USER_MESSAGE = "Creating new User with login [{}]";

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        log.info(CREATE_USER_MESSAGE, user.getLogin());

        user.setId(null);

        getByLogin(user.getLogin())
                .ifPresent(Exceptions.userAlreadyExists());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setRoles(Set.of(User.Role.ROLE_USER));

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
        log.trace(GET_BY_LOGIN_MESSAGE, username);
        return userRepository.findOneByLogin(username);
    }

    @Override
    public void login(String login, WebAuthenticationDetails details) {
        User user = getByLogin(login)
                .orElseThrow(Exceptions.userNotFound(login));

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());

        authToken.setDetails(details);

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Optional<User> getCurrentUserInternal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.trace(GET_CURRENT_MESSAGE, authentication);

        return Optional.ofNullable(authentication)
                .map(Authentication::getName)
                .flatMap(userRepository::findOneByLogin);
    }
}
