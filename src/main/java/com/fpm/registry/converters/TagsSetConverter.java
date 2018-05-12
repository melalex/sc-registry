package com.fpm.registry.converters;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.utils.Tags;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TagsSetConverter extends AbstractConverter<Set<Tag>, Set<String>> {

    @Override
    protected Set<String> convert(Set<Tag> source) {
        return Tags.getNames(source);
    }
}
