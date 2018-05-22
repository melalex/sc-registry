package com.fpm.registry.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DocumentForm {

    @NotNull
    @Size(max = 40)
    private String code;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private String place;
}
