package com.fpm.registry.security;

import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import com.fpm.registry.wrapper.UserDetailsWrapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceAdapter implements UserDetailsService {

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getByLogin(username)
                .map(UserDetailsWrapper::of)
                .orElseThrow(Exceptions.userNotFound(username));
    }
}
