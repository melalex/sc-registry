package com.fpm.registry.converters.reverse;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.services.TagService;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class TagsSetReverseConverter extends ConverterConfigurerSupport<Set<String>, Set<Tag>> {

    private TagService tagService;

    @Override
    protected Converter<Set<String>, Set<Tag>> converter() {
        return new AbstractConverter<>() {

            @Override
            protected Set<Tag> convert(Set<String> source) {
                return tagService.getByNames(source);
            }
        };
    }
}
