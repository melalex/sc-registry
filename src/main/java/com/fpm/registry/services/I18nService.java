package com.fpm.registry.services;

import java.util.Locale;

public interface I18nService {

    String getMessage(String code, Locale locale);

    String getMessage(String code, Locale locale, Object... args);
}
