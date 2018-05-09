package com.fpm.registry.services;

import org.springframework.data.domain.Pageable;

public interface ConfigurationService {

    String getFileStoragePath();

    String getRecycleBinPath();

    int defaultPage();

    int defaultPageSize();

    int maxPageSize();

    int getEncoderStrength();

    Pageable getDefaultPageable();
}
