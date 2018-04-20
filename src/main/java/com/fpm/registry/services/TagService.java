package com.fpm.registry.services;

import com.fpm.registry.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface TagService {

    Set<Tag> getByNames(Set<String> names);

    Page<Tag> getByNameStartsWith(String name, Pageable pageable);
}
