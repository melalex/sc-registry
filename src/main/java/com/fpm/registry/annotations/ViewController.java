package com.fpm.registry.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@RequestMapping
public @interface ViewController {

    @AliasFor("path")
    String[] value() default {};

    @AliasFor(attribute = "value", annotation = RequestMapping.class)
    String[] path() default {};

    @AliasFor(attribute = "value", annotation = Controller.class)
    String id() default "";
}
