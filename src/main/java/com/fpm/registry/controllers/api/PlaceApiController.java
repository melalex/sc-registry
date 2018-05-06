package com.fpm.registry.controllers.api;

import com.fpm.registry.dto.PlaceDto;
import com.fpm.registry.facades.PlaceFacade;
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
@RequestMapping("/api/v1/places")
public class PlaceApiController {

    private PlaceFacade placeFacade;

    @GetMapping
    @Cacheable(Caches.PLACES)
    public Page<PlaceDto> getByNameContains(String name, Pageable pageable) {
        return placeFacade.getByNameContains(name, pageable);
    }
}
