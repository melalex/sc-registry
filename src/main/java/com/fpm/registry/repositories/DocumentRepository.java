package com.fpm.registry.repositories;

import com.fpm.registry.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

//    Page<Document> findAllByCodeContainsAndStatusAndDateBetween(String code, Document.Status status, LocalDate from,
//                                                                LocalDate to, Pageable pageable);
}
