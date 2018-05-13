package com.fpm.registry.utils;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.function.Function;

@UtilityClass
public class Logs {

    public <T> Function<T, T> debug(Logger log, String message) {
        return t -> {
            log.debug(message, t);
            return t;
        };
    }
}
