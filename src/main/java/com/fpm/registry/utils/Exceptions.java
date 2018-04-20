package com.fpm.registry.utils;

import com.fpm.registry.exceptions.ResourceNotFoundException;
import com.fpm.registry.exceptions.UnauthorizedException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class Exceptions {

    private static final String ID_FIELD = "id";
    private static final String NOT_FOUND_ERROR_MESSAGE = "Entity [%s] with %s [%s] not found";
    private static final String UNAUTHORIZED_ERROR_MESSAGE = "Action requires authorization";

    public Supplier<ResourceNotFoundException> notFoundSupplier(Class<?> entity, Object id) {
        return () -> notFound(entity, id);
    }

    public Supplier<ResourceNotFoundException> notFoundSupplier(Class<?> entity, String field, Object id) {
        return () -> notFound(entity, field, id);
    }

    public Supplier<UnauthorizedException> unauthorized() {
        return () -> new UnauthorizedException(UNAUTHORIZED_ERROR_MESSAGE);
    }

    public ResourceNotFoundException notFound(Class<?> entity, Object id) {
        return notFound(entity, ID_FIELD, id);
    }

    public ResourceNotFoundException notFound(Class<?> entity, String field, Object id) {
        return new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, entity.getSimpleName(), field, id));
    }
}
