package com.fpm.registry.services;

import com.fpm.registry.domain.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User getCurrentUser();

    User getCurrentAuditor();

    User getCurrentUserOrNull();

    Optional<User> getByLogin(String username);

    void login(String login, WebAuthenticationDetails details);
}
