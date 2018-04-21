package com.fpm.registry.controllers.api;

import com.fpm.registry.dto.PositionDto;
import com.fpm.registry.facades.PositionFacade;
import com.fpm.registry.utils.Caches;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/positions")
public class PositionController {

    private PositionFacade positionFacade;

    @GetMapping
    @Cacheable(Caches.POSITIONS_CACHE)
    public Page<PositionDto> getAll(Pageable pageable) {
        return positionFacade.getAll(pageable);
    }
}
