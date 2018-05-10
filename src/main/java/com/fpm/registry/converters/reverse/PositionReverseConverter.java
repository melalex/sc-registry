package com.fpm.registry.converters.reverse;

import com.fpm.registry.domain.Position;
import com.fpm.registry.services.PositionService;
import com.github.jmnarloch.spring.boot.modelmapper.ConverterConfigurerSupport;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PositionReverseConverter extends ConverterConfigurerSupport<String, Position> {

    private PositionService positionService;

    @Override
    protected Converter<String, Position> converter() {
        return new AbstractConverter<>() {

            @Override
            protected Position convert(String source) {
                return positionService.getByName(source);
            }
        };
    }
}
