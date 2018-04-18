package com.fpm.registry.services;

import com.fpm.registry.domain.Position;
import org.springframework.data.domain.Page;

public interface PositionService {

    Page<Position> getAll();

    Position getByName(String name);
}
