package com.fpm.registry.services;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

public interface DocumentService {

    Document create(Document document);

    Document commit(Long id);

    Document update(Document document);

    File updateAttachment(Long id, String name, String type);

    Document getById(Long id);

    Page<Document> getByNameStarts(String name, Pageable pageable);

    Page<Document> getByNameStartsForCurrentUser(String name, Pageable pageable);

    Page<Document> getByUserAndNameStartsWith(User user, String name, Pageable pageable);
}
