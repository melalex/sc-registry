package com.fpm.registry.exceptions;

public class ResourceNotFoundException extends RuntimeException{

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
