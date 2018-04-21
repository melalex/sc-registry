package com.fpm.registry.facades;

import com.fpm.registry.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagFacade {

    Page<TagDto> getByNameStartsWith(String name, Pageable pageable);
}
