package com.fpm.registry.problems.converters.impl;

import com.fpm.registry.exceptions.ResourceNotFoundException;
import com.fpm.registry.problems.Problem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class NotFoundProblemConverter extends AbstractProblemConverter<ResourceNotFoundException> {

    @Override
    protected Class<ResourceNotFoundException> getTarget() {
        return ResourceNotFoundException.class;
    }

    @Override
    public Problem convert(ResourceNotFoundException throwable, Locale locale) {
        return null;
    }
}
