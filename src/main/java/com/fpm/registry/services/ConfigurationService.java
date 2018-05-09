package com.fpm.registry.services;

public interface ConfigurationService {

    String getFileStoragePath();

    String getRecycleBinPath();

    int defaultPage();

    int defaultPageSize();

    int maxPageSize();

    int getEncoderStrength();
}
