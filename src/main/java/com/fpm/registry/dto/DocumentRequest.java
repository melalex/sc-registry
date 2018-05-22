package com.fpm.registry.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Data
public class DocumentRequest {
    private String code;
    private LocalDate from;
    private LocalDate to;
    private Pageable pageable;
}
