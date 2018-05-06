package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Country;
import com.fpm.registry.repositories.CountryRepository;
import com.fpm.registry.services.CountryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private static final String GET_BY_NAME_MESSAGE = "Fetching Countries by name [{}]";

    private CountryRepository countryRepository;

    @Override
    public Page<Country> getByNameStartsWith(String name, Pageable pageable) {
        log.trace(GET_BY_NAME_MESSAGE, name);
        return countryRepository.findAllByNameStartsWith(name, pageable);
    }
}
