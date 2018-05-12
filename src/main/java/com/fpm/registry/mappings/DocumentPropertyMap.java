package com.fpm.registry.mappings;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.Tag;
import com.fpm.registry.dto.DocumentDto;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class DocumentPropertyMap extends PropertyMapConfigurerSupport<Document, DocumentDto> {

    private Converter<Set<Tag>, Set<String>> tagsSetConverter;

    @Override
    public PropertyMap<Document, DocumentDto> mapping() {
        return new PropertyMap<>() {

            @Override
            protected void configure() {
                using(tagsSetConverter).map(source.getTags()).setTags(null);
            }
        };
    }
}
