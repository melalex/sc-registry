package com.fpm.registry.utils;

import com.fpm.registry.exceptions.ResourceNotFoundException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class Exceptions {

    private static final String NOT_FOUND_ERROR_MESSAGE = "Entity [%s] with id [%s] not found";

    public Supplier<ResourceNotFoundException> notFoundSupplier(Class<?> entity, Object id) {
        return () -> notFound(entity, id);
    }

    public ResourceNotFoundException notFound(final Class<?> entity, final Object id) {
        return new ResourceNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, entity.getSimpleName(), id));
    }
}
