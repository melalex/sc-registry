package com.fpm.registry.repositories;

import com.fpm.registry.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Page<Country> findAllByNameStartsWith(String name, Pageable pageable);
}
