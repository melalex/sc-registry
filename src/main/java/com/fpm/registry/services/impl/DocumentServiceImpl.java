package com.fpm.registry.services.impl;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import com.fpm.registry.domain.Document;
import com.fpm.registry.dto.DocumentRequest;
import com.fpm.registry.repositories.DocumentRepository;
import com.fpm.registry.services.DocumentService;
import com.fpm.registry.services.MediaService;
import com.fpm.registry.utils.Beans;
import com.fpm.registry.utils.Documents;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final String NEW_DOCUMENT_MESSAGE = "Created new Document: {}";
    private static final String COMMIT_DOCUMENT_MESSAGE = "Committing Document with id [ {} ]";
    private static final String UPDATE_DOCUMENT_MESSAGE = "Updated Document: {}";
    private static final String GET_BY_USER_AND_NAME_MESSAGE = "Searching Documents by code [ {} ]";
    private static final String ALREADY_ACTIVE_MESSAGE = "Document with id [ {} ] already active";
    private static final String CODE_FIELD = "code";

    private DocumentRepository documentRepository;
    private MediaService mediaService;

    @Override
    public Document create(Document document) {
        document.setId(null);
        document.setStatus(Document.Status.INITIAL);

        Document savedDocument = documentRepository.save(document);

        log.info(NEW_DOCUMENT_MESSAGE, savedDocument.getId());
        log.debug(NEW_DOCUMENT_MESSAGE, savedDocument);

        return savedDocument;
    }

    @Override
    public Document commit(Long id) {
        log.info(COMMIT_DOCUMENT_MESSAGE, id);

        Document document = getById(id);
        Document.Status activeStatus = Document.Status.ACTIVE;

        if (activeStatus.equals(document.getStatus())) {
            log.info(ALREADY_ACTIVE_MESSAGE, id);
            return document;
        }

        document.setStatus(activeStatus);

        return documentRepository.save(document);
    }

    @Override
    public void rollback(Long id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Document update(Document document, long id) {
        Document current = getById(id);

        Beans.populateNotNull(document, current);

        Document savedDocument = documentRepository.save(current);

        log.info(UPDATE_DOCUMENT_MESSAGE, savedDocument.getId());
        log.debug(UPDATE_DOCUMENT_MESSAGE, savedDocument);

        return savedDocument;
    }

    @Override
    public File updateAttachment(Long id, String name, String type) {
        Document document = getById(id);
        Documents.linkWithMedia(document);

        MediaService.MediaAndFile mediaAndFile = mediaService.prepareUpdate(document.getAttachment(), name, type);

        Documents.linkWithMedia(document, mediaAndFile.getMedia());

        documentRepository.save(document);

        return mediaAndFile.getFile();
    }

    @Override
    public Document getById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(Exceptions.notFound(Document.class, id));
    }

    @Override
    public Page<Document> getByCodeAndDateRange(DocumentRequest request, Pageable pageable) {
        log.trace(GET_BY_USER_AND_NAME_MESSAGE, request);

        Document example = new Document();

        example.setCode(request.getCode());
        example.setStatus(Document.Status.ACTIVE);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher(CODE_FIELD, contains());

        return documentRepository.findAll(Example.of(example, matcher), pageable);
    }

    @Override
    public Document delete(Long id) {
        Document document = getById(id);

        mediaService.delete(document.getAttachment());
        documentRepository.deleteById(id);
        document.setAttachment(null);

        return document;
    }
}
