package com.fpm.registry.converters;

import com.fpm.registry.domain.Position;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter extends ConverterConfigurerSupport<Position, String> {

    @Override
    protected Converter<Position, String> converter() {
        return new AbstractConverter<>() {

            @Override
            protected String convert(Position source) {
                return source.getName();
            }
        };
    }
}
