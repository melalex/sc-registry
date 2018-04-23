package com.fpm.registry.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DocumentDto {

    private long id;
    private String name;
    private String description;
    private Set<String> tags;
    private PlaceDto place;
    private LocalDate date;
    private UserDto employee;
    private MediaDto attachment;
}
