package com.fpm.registry.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface ApiController {

    @AliasFor("path")
    String[] value() default {};

    @AliasFor(attribute = "value", annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(attribute = "value", annotation = RestController.class)
    String id() default "";
}

