package com.fpm.registry.services.strategy.impl;

import com.fpm.registry.services.strategy.NamingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TimestampNamingStrategy implements NamingStrategy {

    private static final String NAME_FORMAT = "%s-%s";

    private Clock clock;

    @Override
    public String provideName(String baseName) {
        return String.format(NAME_FORMAT, baseName, LocalDateTime.now(clock));
    }
}
