package com.fpm.registry.validators;

import com.fpm.registry.constraints.ValidMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {

    private Set<String> supportedTypes;
    private long allowedSize;

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        supportedTypes = Set.of(constraintAnnotation.types());
        allowedSize = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return isSupportedType(value) && isAllowedSize(value) && !value.isEmpty();
    }

    private boolean isSupportedType(MultipartFile value) {
        return supportedTypes.isEmpty() || supportedTypes.contains(value.getContentType());
    }

    private boolean isAllowedSize(MultipartFile value) {
        return value.getSize() <= allowedSize;
    }
}
