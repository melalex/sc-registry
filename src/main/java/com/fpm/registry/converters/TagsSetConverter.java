package com.fpm.registry.converters;

import com.fpm.registry.domain.Tag;
import com.fpm.registry.utils.Tags;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TagsSetConverter extends ConverterConfigurerSupport<Set<Tag>, Set<String>> {

    @Override
    protected Converter<Set<Tag>, Set<String>> converter() {
        return new AbstractConverter<>() {

            @Override
            protected Set<String> convert(Set<Tag> source) {
                return Tags.getNames(source);
            }
        };
    }
}
