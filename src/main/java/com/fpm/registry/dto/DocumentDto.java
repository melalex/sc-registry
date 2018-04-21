package com.fpm.registry.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.jirutka.validator.collection.constraints.EachNotBlank;
import cz.jirutka.validator.collection.constraints.EachSize;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(value = {"date", "employee", "attachment"}, allowGetters = true)
public class DocumentDto {

    private long id;

    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

    @EachNotBlank
    @Size(max = 6)
    @EachSize(min = 1, max = 40)
    private Set<String> tags;

    private LocalDate date;
    private UserDto employee;
    private MediaDto attachment;
}
