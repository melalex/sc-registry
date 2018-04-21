package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.TagDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.TagFacade;
import com.fpm.registry.services.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Facade
@AllArgsConstructor
public class TagFacadeImpl implements TagFacade {

    private TagService tagService;
    private ExtendedMapper extendedMapper;

    @Override
    public Page<TagDto> getByNameStartsWith(String name, Pageable pageable) {
        return tagService.getByNameStartsWith(name, pageable)
                .map(extendedMapper.mapperFor(TagDto.class));
    }
}
