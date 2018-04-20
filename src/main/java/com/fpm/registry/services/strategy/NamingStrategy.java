package com.fpm.registry.services.strategy;

@FunctionalInterface
public interface NamingStrategy {

    String provideName(String baseName);
}
