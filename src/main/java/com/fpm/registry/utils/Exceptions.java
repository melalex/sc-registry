package com.fpm.registry.utils;

import com.fpm.registry.domain.User;
import com.fpm.registry.exceptions.ResourceNotFoundException;
import com.fpm.registry.exceptions.UnauthorizedException;
import com.fpm.registry.exceptions.UserAlreadyExistsException;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@UtilityClass
public class Exceptions {

    private static final String ID_FIELD = "id";
    private static final String UNAUTHORIZED_ERROR_MESSAGE = "Action requires authorization";
    private static final String USER_NOT_FOUNT_ERROR_MESSAGE = "User with login [%s] not found";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User with login [%s] already exists";

    private static final String ERROR_FORMAT = "error.%s.message";

    public Supplier<ResourceNotFoundException> notFound(Class<?> entity, Object id) {
        return notFound(entity, ID_FIELD, id);
    }

    public Supplier<ResourceNotFoundException> notFound(Class<?> entity, String field, Object id) {
        return () -> new ResourceNotFoundException(entity.getSimpleName(), field, Objects.toString(id));
    }

    public Supplier<UnauthorizedException> unauthorized() {
        return () -> new UnauthorizedException(UNAUTHORIZED_ERROR_MESSAGE);
    }

    public Supplier<UsernameNotFoundException> userNotFound(String username) {
        return () -> new UsernameNotFoundException(String.format(USER_NOT_FOUNT_ERROR_MESSAGE, username));
    }

    public Consumer<User> userAlreadyExists() {
        return u -> {
            var login = u.getLogin();
            throw new UserAlreadyExistsException(login, String.format(USER_ALREADY_EXISTS_ERROR_MESSAGE, login));
        };
    }

    public String getErrorCode(Throwable throwable) {
        return getErrorCode(throwable.getClass());
    }

    public static String getErrorCode(Class<? extends Throwable> clazz) {
        return String.format(ERROR_FORMAT, clazz.getSimpleName());
    }
}
