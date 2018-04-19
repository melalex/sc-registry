package com.fpm.registry.facades;

import com.fpm.registry.dto.PositionDto;
import org.springframework.data.domain.Page;

public interface PositionFacade {

    Page<PositionDto> getAll();
}
