package com.fpm.registry.security;

import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import com.fpm.registry.wrapper.UserDetailsWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getByLogin(username)
                .map(UserDetailsWrapper::of)
                .orElseThrow(Exceptions.userNotFound(username));
    }
}
