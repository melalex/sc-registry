package com.fpm.registry.converters.reverse;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.services.TagService;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class TagsSetReverseConverter extends AbstractConverter<Set<String>, Set<Tag>> {

    private TagService tagService;

    @Override
    protected Set<Tag> convert(Set<String> source) {
        return tagService.getByNames(source);
    }
}
