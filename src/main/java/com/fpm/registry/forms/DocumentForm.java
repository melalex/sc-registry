package com.fpm.registry.forms;

import com.fpm.registry.constraints.EachSize;
import lombok.Data;

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

    @Size(max = 6)
    @EachSize(min = 1, max = 40, notNull = true)
    private Set<String> tags;

    @NotNull
    private String place;
}
