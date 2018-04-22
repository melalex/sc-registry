package com.fpm.registry;

import com.fpm.registry.services.strategy.NamingStrategy;
import com.fpm.registry.services.strategy.impl.NormalizationDecorator;
import com.fpm.registry.services.strategy.impl.TimestampNamingStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class RegistryConfiguration {

    @Bean
    public TimestampNamingStrategy timestampNamingStrategy() {
        return new TimestampNamingStrategy(clock());
    }

    @Bean
    public NamingStrategy namingStrategy() {
        return new NormalizationDecorator(timestampNamingStrategy());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
