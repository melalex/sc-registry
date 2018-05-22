package com.fpm.registry;

import com.fpm.registry.services.ConfigurationService;
import com.fpm.registry.utils.Urls;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;

@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private static final String LANG_PARAMETER_NAME = "lang";
    private static final String PAGE_PARAMETER_NAME = "page";
    private static final String SIZE_PARAMETER_NAME = "limit";

    private ConfigurationService configurationService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        int defaultPage = configurationService.defaultPage();
        int defaultPageSize = configurationService.defaultPageSize();
        int maxPageSize = configurationService.maxPageSize();

        resolver.setOneIndexedParameters(true);
        resolver.setPrefix(StringUtils.EMPTY);
        resolver.setPageParameterName(PAGE_PARAMETER_NAME);
        resolver.setSizeParameterName(SIZE_PARAMETER_NAME);
        resolver.setMaxPageSize(maxPageSize);
        resolver.setFallbackPageable(PageRequest.of(defaultPage, defaultPageSize));

        argumentResolvers.add(resolver);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Urls.LOGIN).setViewName(Views.SIGN_IN);
        registry.addViewController(Urls.CREATE_DOCUMENT).setViewName(Views.DOCUMENT_EDIT);
        registry.addRedirectViewController(Urls.ROOT, Urls.INDEX);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(LANG_PARAMETER_NAME);
        return localeChangeInterceptor;
    }
}
