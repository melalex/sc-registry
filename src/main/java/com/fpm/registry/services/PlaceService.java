package com.fpm.registry.services;

import com.fpm.registry.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceService {

    Page<Place> getByCountryIsoAndNameStartsWith(String iso, String name, Pageable pageable);

    Place getById(Long id);
}
