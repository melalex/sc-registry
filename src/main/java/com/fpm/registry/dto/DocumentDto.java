package com.fpm.registry.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentDto {

    private long id;
    private String code;
    private String description;
    private PlaceDto place;
    private LocalDate date;
    private UserDto employee;
    private MediaDto attachment;
}
