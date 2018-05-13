package com.fpm.registry.services;

import org.springframework.data.domain.Pageable;

public interface ConfigurationService {

    String getFileStoragePath();

    String getRecycleBinPath();

    String getTempDirectoryPath();

    int defaultPage();

    int defaultPageSize();

    int maxPageSize();

    int getEncoderStrength();

    Pageable getDefaultPageable();

    long maxAttachmentSize();
}
