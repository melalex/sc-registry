package com.fpm.registry.services.impl;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import com.fpm.registry.repositories.DocumentRepository;
import com.fpm.registry.services.DocumentService;
import com.fpm.registry.services.UserService;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private static final String NAME_FIELD = "name";

    private DocumentRepository documentRepository;
    private UserService userService;

    @Override
    public Document create(final Document document) {
        document.setId(null);
        return documentRepository.save(document);
    }

    @Override
    public Document update(final Document document) {
        var id = document.getId();

        if (documentRepository.existsById(id)) {
            throw Exceptions.notFound(Document.class, id);
        }

        return documentRepository.save(document);
    }

    @Override
    public Document getById(final Long id) {
        return documentRepository.findById(id)
                .orElseThrow(Exceptions.notFoundSupplier(Document.class, id));
    }

    @Override
    public Page<Document> getByNameStarts(final String name, final Pageable pageable) {
        return getByUserAndNameStartsWith(null, name, pageable);
    }

    @Override
    public Page<Document> getByNameStartsForCurrentUser(final String name, final Pageable pageable) {
        return getByUserAndNameStartsWith(userService.getCurrentUser(), name, pageable);
    }

    @Override
    public Page<Document> getByUserAndNameStartsWith(User user, String name, Pageable pageable) {
        var matcher = ExampleMatcher.matching().withMatcher(NAME_FIELD, startsWith());
        var document = new Document(name, user);

        return documentRepository.findAll(Example.of(document, matcher), pageable);
    }
}
