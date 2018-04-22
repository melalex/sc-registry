package com.fpm.registry.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User with given login already exists")
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -7523880112951966688L;

    @Getter
    private String rejected;

    public UserAlreadyExistsException(String rejected) {
        this.rejected = rejected;
    }

    public UserAlreadyExistsException(String rejected, String message) {
        super(message);
        this.rejected = rejected;
    }

    public UserAlreadyExistsException(String rejected, String message, Throwable cause) {
        super(message, cause);
        this.rejected = rejected;
    }

    public UserAlreadyExistsException(String rejected, Throwable cause) {
        super(cause);
        this.rejected = rejected;
    }
}
