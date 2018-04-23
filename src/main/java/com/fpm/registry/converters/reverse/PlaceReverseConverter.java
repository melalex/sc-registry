package com.fpm.registry.converters.reverse;

import com.fpm.registry.domain.Place;
import com.fpm.registry.services.PlaceService;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlaceReverseConverter extends ConverterConfigurerSupport<Long, Place> {

    private PlaceService placeService;

    @Override
    protected Converter<Long, Place> converter() {
        return new AbstractConverter<>() {

            @Override
            protected Place convert(Long source) {
                return placeService.getById(source);
            }
        };
    }
}
