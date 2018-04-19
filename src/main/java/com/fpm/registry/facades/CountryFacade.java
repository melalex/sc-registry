package com.fpm.registry.facades;

import com.fpm.registry.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryFacade {

    Page<CountryDto> getByNameStartsWith(String name, Pageable pageable);
}
