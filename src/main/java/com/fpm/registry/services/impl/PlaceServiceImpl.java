package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Place;
import com.fpm.registry.repositories.PlaceRepository;
import com.fpm.registry.services.PlaceService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private static final String FIND_BY_COUNTRY_AND_NAME_MESSAGE = "Fetching places by country [{}] and name [{}]";
    private static final String FIND_BY_ID_MESSAGE = "Fetching places by id [{}]";

    private PlaceRepository placeRepository;

    @Override
    public Page<Place> getByCountryIsoAndNameStartsWith(String iso, String name, Pageable pageable) {
        log.trace(FIND_BY_COUNTRY_AND_NAME_MESSAGE, iso, name);
        return placeRepository.findAllByCountryIsoAndNameStartingWith(iso, name, pageable);
    }

    @Override
    public Place getById(Long id) {
        log.trace(FIND_BY_ID_MESSAGE, id);
        return placeRepository.findById(id)
                .orElseThrow(Exceptions.notFound(Place.class, id));
    }
}
