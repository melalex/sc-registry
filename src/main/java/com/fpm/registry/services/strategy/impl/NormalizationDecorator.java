package com.fpm.registry.services.strategy.impl;

import com.fpm.registry.services.strategy.NamingStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NormalizationDecorator implements NamingStrategy {

    private NamingStrategy decorated;

    @Override
    public String provideName(String baseName) {
        String normalized = baseName.replaceAll("..|\\|/", "");

        return decorated.provideName(normalized);
    }
}
