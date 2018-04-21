package com.fpm.registry;

import com.fpm.registry.services.ConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private ConfigurationService configurationService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        var resolver = new PageableHandlerMethodArgumentResolver();
        var defaultPage = configurationService.defaultPage();
        var defaultPageSize = configurationService.defaultPageSize();

        resolver.setOneIndexedParameters(true);
        resolver.setFallbackPageable(PageRequest.of(defaultPage, defaultPageSize));

        argumentResolvers.add(resolver);
    }
}
