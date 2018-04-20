package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Position;
import com.fpm.registry.repositories.PositionRepository;
import com.fpm.registry.services.PositionService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private static final String NAME_FIELD = "name";

    private PositionRepository positionRepository;

    @Override
    public Page<Position> getAll(Pageable pageable) {
        return positionRepository.findAll(pageable);
    }

    @Override
    public Position getByName(String name) {
        return positionRepository.findOneByName(name)
                .orElseThrow(Exceptions.notFoundSupplier(Position.class, NAME_FIELD, name));
    }
}
