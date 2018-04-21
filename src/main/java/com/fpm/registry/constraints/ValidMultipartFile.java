package com.fpm.registry.constraints;

import com.fpm.registry.validators.MultipartFileValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileValidator.class)
@Documented
public @interface ValidMultipartFile {

    String message() default "{constraints.validMultipartFile}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] types() default {};

    long size() default Long.MAX_VALUE;
}
