package com.fpm.registry.facades;

import com.fpm.registry.dto.DocumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentFacade {

    DocumentDto create(DocumentDto document);

    DocumentDto update(DocumentDto document);

    DocumentDto getById(Long id);

    Page<DocumentDto> getByNameStarts(String name, Pageable pageable);

    Page<DocumentDto> getByNameStartsForCurrentUser(String name, Pageable pageable);
}
