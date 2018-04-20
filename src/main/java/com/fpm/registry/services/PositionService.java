package com.fpm.registry.services;

import com.fpm.registry.domain.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionService {

    Page<Position> getAll(Pageable pageable);

    Position getByName(String name);
}
