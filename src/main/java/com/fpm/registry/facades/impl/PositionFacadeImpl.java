package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.PositionDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.PositionFacade;
import com.fpm.registry.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Facade
@AllArgsConstructor
public class PositionFacadeImpl implements PositionFacade {

    private PositionService positionService;
    private ExtendedMapper extendedMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<PositionDto> getAll() {
        return positionService.getAll()
                .map(extendedMapper.mapperFor(PositionDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PositionDto> getAll(Pageable pageable) {
        return positionService.getAll(pageable)
                .map(extendedMapper.mapperFor(PositionDto.class));
    }
}
