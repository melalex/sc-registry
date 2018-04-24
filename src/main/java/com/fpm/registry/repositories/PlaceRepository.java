package com.fpm.registry.repositories;

import com.fpm.registry.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Page<Place> findAllByCanonicalNameContains(String name, Pageable pageable);

    Optional<Place> findByCanonicalName(String canonicalName);
}
