package com.fpm.registry.services;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {

    Document create(Document document);

    Document update(Document document);

    Document getById(Long id);

    Page<Document> getByNameStarts(String name, Pageable pageable);

    Page<Document> getByNameStartsForCurrentUser(String name, Pageable pageable);

    Page<Document> getByUserAndNameStartsWith(User user, String name, Pageable pageable);
}
