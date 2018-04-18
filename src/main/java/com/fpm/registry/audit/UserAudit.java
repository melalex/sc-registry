package com.fpm.registry.audit;

import com.fpm.registry.domain.User;
import com.fpm.registry.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserAudit implements AuditorAware<User> {

    private UserService userService;

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(userService.getCurrentUser());
    }
}
