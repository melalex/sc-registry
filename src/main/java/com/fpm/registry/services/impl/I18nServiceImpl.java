package com.fpm.registry.services.impl;

import com.fpm.registry.services.I18nService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class I18nServiceImpl implements I18nService {

    private MessageSource messageSource;

    @Override
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, new Object[0], locale);
    }

    @Override
    public String getMessage(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }
}
