package com.fpm.registry.services;

import com.fpm.registry.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceService {

    Page<Place> getByNameContains(String name, Pageable pageable);

    Place getByCanonicalName(String name);
}
