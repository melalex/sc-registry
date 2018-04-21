package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.PlaceDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.PlaceFacade;
import com.fpm.registry.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Facade
@AllArgsConstructor
public class PlaceFacadeImpl implements PlaceFacade {

    private PlaceService placeService;
    private ExtendedMapper extendedMapper;

    @Override
    public Page<PlaceDto> getByCountryIsoAndNameStartsWith(String iso, String name, Pageable pageable) {
        return placeService.getByCountryIsoAndNameStartsWith(iso, name, pageable)
                .map(extendedMapper.mapperFor(PlaceDto.class));
    }
}