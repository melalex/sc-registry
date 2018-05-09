package com.fpm.registry.facades;

import com.fpm.registry.dto.PositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionFacade {

    Page<PositionDto> getAll();

    Page<PositionDto> getAll(Pageable pageable);
}
