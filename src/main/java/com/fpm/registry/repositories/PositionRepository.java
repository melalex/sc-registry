package com.fpm.registry.repositories;

import com.fpm.registry.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findOneByName(String name);
}
