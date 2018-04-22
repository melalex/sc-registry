package com.fpm.registry.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;

@UtilityClass
public class Maps {

    private static final String GET_ERROR_MESSAGE = "Map doesn't contains key '%s'";

    public <K, V> V getOrThrow(Map<K, V> map, K key) {
        return Optional.ofNullable(map.get(key))
                .orElseThrow(() -> new IllegalArgumentException(String.format(GET_ERROR_MESSAGE, key)));
    }
}
