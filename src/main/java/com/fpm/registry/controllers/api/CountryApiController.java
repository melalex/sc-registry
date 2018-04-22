package com.fpm.registry.controllers.api;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.dto.CountryDto;
import com.fpm.registry.facades.CountryFacade;
import com.fpm.registry.utils.Caches;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@ApiController("/api/v1/countries")
public class CountryApiController {

    private CountryFacade countryFacade;

    @GetMapping
    @Cacheable(Caches.COUNTRIES)
    public Page<CountryDto> getByNameStartsWith(String name, Pageable pageable) {
        return countryFacade.getByNameStartsWith(name, pageable);
    }
}
