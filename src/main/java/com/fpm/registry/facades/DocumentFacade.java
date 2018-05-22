package com.fpm.registry.facades;

import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.dto.DocumentRequest;
import com.fpm.registry.forms.DocumentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

public interface DocumentFacade {

    DocumentDto create(DocumentForm document);

    void commit(Long id);

    void rollback(Long id);

    DocumentDto update(DocumentForm document, long id);

    File updateAttachment(Long id, String name, String type);

    DocumentDto getById(Long id);

    Page<DocumentDto> getByCodeAndDateRange(DocumentRequest request, Pageable pageable);

    void delete(Long id);
}
