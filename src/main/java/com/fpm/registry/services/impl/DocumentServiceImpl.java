package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.DocumentRepository;
import com.fpm.registry.services.DocumentService;
import com.fpm.registry.services.MediaService;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final String NEW_DOCUMENT_MESSAGE = "Created new Document: {}";
    private static final String UPDATE_DOCUMENT_MESSAGE = "Updated Document: {}";
    private static final String GET_BY_USER_AND_NAME_MESSAGE = "Searching Documents by employee [{}] and name [{}]";
    private static final String GET_BY_NAME_MESSAGE = "Searching Documents by name [{}]";

    private DocumentRepository documentRepository;
    private UserService userService;
    private MediaService mediaService;

    @Override
    public Document create(Document document) {
        document.setId(null);
        document.setStatus(Document.Status.INITIAL);

        var savedDocument = documentRepository.save(document);

        log.info(NEW_DOCUMENT_MESSAGE, savedDocument.getId());
        log.debug(NEW_DOCUMENT_MESSAGE, savedDocument);

        return savedDocument;
    }

    @Override
    public Document commit(Long id) {
        var document = getById(id);
        document.setStatus(Document.Status.ACTIVE);

        return documentRepository.save(document);
    }

    @Override
    public Document update(Document document) {
        var id = document.getId();

        if (documentRepository.existsById(id)) {
            throw Exceptions.notFound(Document.class, id);
        }

        var savedDocument = documentRepository.save(document);

        log.info(UPDATE_DOCUMENT_MESSAGE, savedDocument.getId());
        log.debug(UPDATE_DOCUMENT_MESSAGE, savedDocument);

        return savedDocument;
    }

    @Override
    public File updateAttachment(Long id, String name, String type) {
        var document = getById(id);
        var mediaAndFile = mediaService.prepareUpdate(document.getAttachment(), name, type);

        document.setAttachment(mediaAndFile.getMedia());

        documentRepository.save(document);

        return mediaAndFile.getFile();
    }

    @Override
    public Document getById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(Exceptions.notFoundSupplier(Document.class, id));
    }

    @Override
    public Page<Document> getByNameStarts(String name, Pageable pageable) {
        log.trace(GET_BY_NAME_MESSAGE, name);

        return documentRepository.findAllNameStartingWith(name, pageable);
    }

    @Override
    public Page<Document> getByNameStartsForCurrentUser(String name, Pageable pageable) {

        return getByUserAndNameStartsWith(userService.getCurrentUser(), name, pageable);
    }

    @Override
    public Page<Document> getByUserAndNameStartsWith(User user, String name, Pageable pageable) {
        log.trace(GET_BY_USER_AND_NAME_MESSAGE, user.getLogin(), name);

        return documentRepository.findAllByEmployeeAndNameStartingWith(user, name, pageable);
    }
}
