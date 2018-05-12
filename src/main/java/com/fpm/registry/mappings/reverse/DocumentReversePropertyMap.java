package com.fpm.registry.mappings.reverse;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.Tag;
import com.fpm.registry.forms.DocumentForm;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class DocumentReversePropertyMap extends PropertyMapConfigurerSupport<DocumentForm, Document> {

    private Converter<Set<String>, Set<Tag>> tagsSetReverseConverter;

    @Override
    public PropertyMap<DocumentForm, Document> mapping() {
        return new PropertyMap<>() {

            @Override
            protected void configure() {
                using(tagsSetReverseConverter).map(source.getTags()).setTags(null);
            }
        };
    }
}
