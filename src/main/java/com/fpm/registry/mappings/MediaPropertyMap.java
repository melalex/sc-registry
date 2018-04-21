package com.fpm.registry.mappings;

import com.fpm.registry.domain.Media;
import com.fpm.registry.dto.MediaDto;
import com.github.jmnarloch.spring.boot.modelmapper.PropertyMapConfigurerSupport;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class MediaPropertyMap extends PropertyMapConfigurerSupport<Media, MediaDto> {

    @Override
    public PropertyMap<Media, MediaDto> mapping() {
        return new PropertyMap<>() {

            @Override
            protected void configure() {
                map().setName(source.getOriginalName());
            }
        };
    }
}
