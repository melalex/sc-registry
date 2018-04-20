package com.fpm.registry.services.impl;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.DocumentRepository;
import com.fpm.registry.services.DocumentService;
import com.fpm.registry.services.MediaService;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final String NAME_FIELD = "name";

    private DocumentRepository documentRepository;
    private UserService userService;
    private MediaService mediaService;

    @Override
    public Document create(Document document) {
        document.setId(null);
        document.setStatus(Document.Status.INITIAL);

        return documentRepository.save(document);
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

        return documentRepository.save(document);
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
        return getByUserAndNameStartsWith(null, name, pageable);
    }

    @Override
    public Page<Document> getByNameStartsForCurrentUser(String name, Pageable pageable) {
        return getByUserAndNameStartsWith(userService.getCurrentUser(), name, pageable);
    }

    @Override
    public Page<Document> getByUserAndNameStartsWith(User user, String name, Pageable pageable) {
        var matcher = ExampleMatcher.matching().withMatcher(NAME_FIELD, startsWith());
        var document = new Document();

        return documentRepository.findAll(Example.of(document, matcher), pageable);
    }
}
