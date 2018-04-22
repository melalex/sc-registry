package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.CountryDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.CountryFacade;
import com.fpm.registry.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Facade
@AllArgsConstructor
public class CountryFacadeImpl implements CountryFacade {

    private ExtendedMapper extendedMapper;
    private CountryService countryService;

    @Override
    @Transactional(readOnly = true)
    public Page<CountryDto> getByNameStartsWith(String name, Pageable pageable) {
        return countryService.getByNameStartsWith(name, pageable)
                .map(extendedMapper.mapperFor(CountryDto.class));
    }
}
