package com.fpm.registry;

import com.fpm.registry.services.ConfigurationService;
import com.fpm.registry.services.strategy.NamingStrategy;
import com.fpm.registry.services.strategy.impl.NormalizationDecorator;
import com.fpm.registry.services.strategy.impl.TimestampNamingStrategy;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.CharsetNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.time.Clock;

@Configuration
public class RegistryConfiguration {

    @Setter(onMethod = @__(@Autowired))
    private ConfigurationService configurationService;

    @Bean
    public TimestampNamingStrategy timestampNamingStrategy() {
        return new TimestampNamingStrategy(clock());
    }

    @Bean
    public NamingStrategy namingStrategy() {
        return new NormalizationDecorator(timestampNamingStrategy());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    @SneakyThrows
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(configurationService.maxAttachmentSize());
        resolver.setDefaultEncoding(CharsetNames.UTF_8);
        resolver.setUploadTempDir(new FileSystemResource(configurationService.getTempDirectoryPath()));
        return resolver;
    }
}
