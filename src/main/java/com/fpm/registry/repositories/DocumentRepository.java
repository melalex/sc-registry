package com.fpm.registry.repositories;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Page<Document> findAllNameStartingWith(String name, Pageable pageable);

    Page<Document> findAllByEmployeeAndNameStartingWith(User user, String name, Pageable pageable);
}
