package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.PlaceDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.PlaceFacade;
import com.fpm.registry.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Facade
@AllArgsConstructor
public class PlaceFacadeImpl implements PlaceFacade {

    private PlaceService placeService;
    private ExtendedMapper extendedMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<PlaceDto> getByNameContains(String name, Pageable pageable) {
        return placeService.getByNameContains(name, pageable)
                .map(extendedMapper.mapperFor(PlaceDto.class));
    }
}
