package com.fpm.registry.controllers.api;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.dto.PlaceDto;
import com.fpm.registry.facades.PlaceFacade;
import com.fpm.registry.utils.Caches;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@ApiController("/api/v1/places")
public class PlaceApiController {

    private PlaceFacade placeFacade;

    @GetMapping
    @Cacheable(Caches.PLACES_CACHE)
    public Page<PlaceDto> getByCountryIsoAndNameStartsWith(String iso, String name, Pageable pageable) {
        return placeFacade.getByCountryIsoAndNameStartsWith(iso, name, pageable);
    }
}
