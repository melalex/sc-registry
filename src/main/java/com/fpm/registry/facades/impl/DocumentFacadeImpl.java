package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.domain.Document;
import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.forms.DocumentForm;
import com.fpm.registry.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Facade
@AllArgsConstructor
public class DocumentFacadeImpl implements DocumentFacade {

    private DocumentService documentService;
    private ExtendedMapper extendedMapper;

    @Override
    @Transactional
    public DocumentDto create(DocumentForm document) {
        var toSave = extendedMapper.map(document, Document.class);
        var saved = documentService.create(toSave);

        return extendedMapper.map(saved, DocumentDto.class);
    }

    @Override
    @Transactional
    public void commit(Long id) {
        documentService.commit(id);
    }

    @Override
    @Transactional
    public void rollback(Long id) {
        documentService.rollback(id);
    }

    @Override
    @Transactional
    public DocumentDto update(DocumentDto document) {
        return extendedMapper.wrapEntity(document, Document.class, documentService::update);
    }

    @Override
    @Transactional
    public File updateAttachment(Long id, String name, String type) {
        return documentService.updateAttachment(id, name, type);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentDto getById(Long id) {
        var entity = documentService.getById(id);
        return extendedMapper.map(entity, DocumentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentDto> getByNameContains(String name, Pageable pageable) {
        return documentService.getByNameContains(name, pageable)
                .map(extendedMapper.mapperFor(DocumentDto.class));
    }

}
