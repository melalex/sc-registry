package com.fpm.registry.services;

import com.fpm.registry.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Page<Country> getByNameStartsWith(String name, Pageable pageable);
}
