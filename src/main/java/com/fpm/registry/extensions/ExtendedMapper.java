package com.fpm.registry.extensions;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class ExtendedMapper {

    @Delegate
    private ModelMapper modelMapper;

    public <S, T> Function<S, T> mapperFor(Class<T> target) {
        return s -> map(s, target);
    }

    public <D, E> D wrapEntity(D dto, Class<E> entityClass, Function<E, E> action) {
        var entity = map(dto, entityClass);
        var result = action.apply(entity);

        @SuppressWarnings("unchecked")
        var dtoClass = (Class<D>) dto.getClass();

        return map(result, dtoClass);
    }
}
