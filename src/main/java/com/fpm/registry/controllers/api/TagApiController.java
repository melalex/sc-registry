package com.fpm.registry.controllers.api;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.dto.TagDto;
import com.fpm.registry.facades.TagFacade;
import com.fpm.registry.utils.Caches;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@ApiController("/api/v1/tags")
public class TagApiController {

    private TagFacade tagFacade;

    @GetMapping
    @Cacheable(Caches.TAGS_CACHE)
    public Page<TagDto> getByNameStartsWith(String name, Pageable pageable) {
        return tagFacade.getByNameStartsWith(name, pageable);
    }
}
