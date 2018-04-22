package com.fpm.registry.services;

import com.fpm.registry.domain.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User getCurrentUser();

    User getCurrentUserOrNull();

    Optional<User> getByLogin(String username);
}
