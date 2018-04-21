package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.domain.Document;
import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.extensions.ExtendedMapper;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

@Facade
@AllArgsConstructor
public class DocumentFacadeImpl implements DocumentFacade {

    private DocumentService documentService;
    private ExtendedMapper extendedMapper;

    @Override
    public DocumentDto create(DocumentDto document) {
        return extendedMapper.wrapEntity(document, Document.class, documentService::create);
    }

    @Override
    public DocumentDto commit(Long id) {
        var committed = documentService.commit(id);
        return extendedMapper.map(committed, DocumentDto.class);
    }

    @Override
    public DocumentDto update(DocumentDto document) {
        return extendedMapper.wrapEntity(document, Document.class, documentService::update);
    }

    @Override
    public File updateAttachment(Long id, String name, String type) {
        return documentService.updateAttachment(id, name, type);
    }

    @Override
    public DocumentDto getById(Long id) {
        var entity = documentService.getById(id);
        return extendedMapper.map(entity, DocumentDto.class);
    }

    @Override
    public Page<DocumentDto> getByNameStarts(String name, Pageable pageable) {
        return documentService.getByNameStarts(name, pageable)
                .map(extendedMapper.mapperFor(DocumentDto.class));
    }

    @Override
    public Page<DocumentDto> getByNameStartsForCurrentUser(String name, Pageable pageable) {
        return documentService.getByNameStartsForCurrentUser(name, pageable)
                .map(extendedMapper.mapperFor(DocumentDto.class));
    }
}
