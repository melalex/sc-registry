package com.fpm.registry.repositories;

import com.fpm.registry.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Page<Place> findAllByCountryIsoAndNameStartingWith(String iso, String name, Pageable pageable);
}
