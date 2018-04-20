package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Position;
import com.fpm.registry.repositories.PositionRepository;
import com.fpm.registry.services.PositionService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

    private static final String GET_ALL_MESSAGE = "Getting all positions with pageable [{}]";
    private static final String GET_BY_NAME_MESSAGE = "Get position by name [{}]";
    private static final String NAME_FIELD = "name";

    private PositionRepository positionRepository;

    @Override
    public Page<Position> getAll(Pageable pageable) {
        log.trace(GET_ALL_MESSAGE, pageable);
        return positionRepository.findAll(pageable);
    }

    @Override
    public Position getByName(String name) {
        log.trace(GET_BY_NAME_MESSAGE, name);
        return positionRepository.findOneByName(name)
                .orElseThrow(Exceptions.notFoundSupplier(Position.class, NAME_FIELD, name));
    }
}
