package com.fpm.registry.controllers.api;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.dto.PositionDto;
import com.fpm.registry.facades.PositionFacade;
import com.fpm.registry.utils.Caches;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@ApiController("/api/v1/positions")
public class PositionApiController {

    private PositionFacade positionFacade;

    @GetMapping
    @Cacheable(Caches.POSITIONS)
    public Page<PositionDto> getAll(Pageable pageable) {
        return positionFacade.getAll(pageable);
    }
}
