package com.fpm.registry.services;

import com.fpm.registry.domain.Document;
import com.fpm.registry.dto.DocumentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

public interface DocumentService {

    Document create(Document document);

    Document commit(Long id);

    void rollback(Long id);

    Document update(Document document, long id);

    File updateAttachment(Long id, String name, String type);

    Document getById(Long id);

    Page<Document> getByCodeAndDateRange(DocumentRequest request, Pageable pageable);

    Document delete(Long id);
}
