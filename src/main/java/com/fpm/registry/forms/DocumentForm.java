package com.fpm.registry.forms;

import cz.jirutka.validator.collection.constraints.EachNotBlank;
import cz.jirutka.validator.collection.constraints.EachSize;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DocumentForm {

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

    @NonNull
    private Long place;
}
