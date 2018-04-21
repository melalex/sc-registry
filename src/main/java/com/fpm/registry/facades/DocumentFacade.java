package com.fpm.registry.facades;

import com.fpm.registry.dto.DocumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

public interface DocumentFacade {

    DocumentDto create(DocumentDto document);

    DocumentDto commit(Long id);

    DocumentDto update(DocumentDto document);

    File updateAttachment(Long id, String name, String type);

    DocumentDto getById(Long id);

    Page<DocumentDto> getByNameStarts(String name, Pageable pageable);

    Page<DocumentDto> getByNameStartsForCurrentUser(String name, Pageable pageable);
}
