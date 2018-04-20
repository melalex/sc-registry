package com.fpm.registry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested resource not found")
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7094989224723998536L;

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
