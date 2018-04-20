package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Country;
import com.fpm.registry.repositories.CountryRepository;
import com.fpm.registry.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Override
    public Page<Country> getByNameStartsWith(String name, Pageable pageable) {
        return countryRepository.findAllByByNameStartsWith(name, pageable);
    }
}
