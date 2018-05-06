package com.fpm.registry.extensions;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
        E entity = map(dto, entityClass);
        E result = action.apply(entity);

        @SuppressWarnings("unchecked")
        Class<D> dtoClass = (Class<D>) dto.getClass();

        return map(result, dtoClass);
    }

    public <S, D> D mapNullable(S source, Class<D> clazz) {
        return Optional.ofNullable(source)
                .map(mapperFor(clazz))
                .orElse(null);
    }
}
