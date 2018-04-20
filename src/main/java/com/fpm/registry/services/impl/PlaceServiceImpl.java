package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Place;
import com.fpm.registry.repositories.PlaceRepository;
import com.fpm.registry.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private PlaceRepository placeRepository;

    @Override
    public Page<Place> getByCountryIsoAndNameStartsWith(String iso, String name, Pageable pageable) {
        return placeRepository.findAllByCountryIsoAndNameStartingWith(iso, name, pageable);
    }
}
