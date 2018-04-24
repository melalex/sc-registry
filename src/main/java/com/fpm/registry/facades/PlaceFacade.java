package com.fpm.registry.facades;

import com.fpm.registry.dto.PlaceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceFacade {

    Page<PlaceDto> getByNameContains(String name, Pageable pageable);
}
