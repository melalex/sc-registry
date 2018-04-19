package com.fpm.registry.facades;

import com.fpm.registry.dto.TagDto;
import org.springframework.data.domain.Page;

public interface TagFacade {

    Page<TagDto> getByNameStartsWith(String name);
}
