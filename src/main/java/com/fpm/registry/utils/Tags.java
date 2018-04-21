package com.fpm.registry.utils;

import com.fpm.registry.domain.Tag;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class Tags {

    public Set<String> getNames(Collection<Tag> tags) {
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toUnmodifiableSet());
    }
}
