package com.fpm.registry;

import static java.util.Map.entry;

import com.fpm.registry.exceptions.UserAlreadyExistsException;
import com.fpm.registry.problems.converters.ProblemConversionService;
import com.fpm.registry.problems.converters.ProblemConverter;
import com.fpm.registry.problems.converters.impl.ProblemConversionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class ProblemsConfiguration {

    private ProblemConverter<UserAlreadyExistsException> userAlreadyExistsConverter;

    @Bean
    public ProblemConversionService problemConversionService() {
        return new ProblemConversionServiceImpl(Map.ofEntries(
                entry(UserAlreadyExistsException.class, userAlreadyExistsConverter)
        ));
    }
}
