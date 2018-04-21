package com.fpm.registry.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"id", "date", "employee", "attachment"}, allowGetters = true)
public class DocumentDto {

    private long id;
    private String name;
    private String description;
    private Set<String> tags;
    private LocalDate date;
    private UserDto employee;
    private MediaDto attachment;
}
