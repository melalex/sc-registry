package com.fpm.registry.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Requested resource not found")
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7094989224723998536L;

    private static final String NOT_FOUND_ERROR_MESSAGE = "Entity [%s] with %s [%s] not found";

    private String entityName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String entityName, String fieldName, String fieldValue) {
        super(String.format(NOT_FOUND_ERROR_MESSAGE, entityName, fieldName, fieldValue));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
