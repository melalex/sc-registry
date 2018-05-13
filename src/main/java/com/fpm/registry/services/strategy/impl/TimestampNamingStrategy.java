package com.fpm.registry.services.strategy.impl;

import com.fpm.registry.services.strategy.NamingStrategy;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class TimestampNamingStrategy implements NamingStrategy {

    private static final String NAME_FORMAT = "%s-%s";
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    private Clock clock;

    @Override
    public String provideName(String baseName) {
        String[] splitted = baseName.split("\\.");
        String name = splitted[0];
        String time = LocalDateTime.now(clock).format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        StringBuilder resultBuilder = new StringBuilder(String.format(NAME_FORMAT, name, time));

        if (splitted.length >= 2) {
            resultBuilder.append(".").append(splitted[1]);
        }

        return resultBuilder.toString();
    }
}
