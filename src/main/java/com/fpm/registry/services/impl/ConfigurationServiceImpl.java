package com.fpm.registry.services.impl;

import com.fpm.registry.RegistryProperties;
import com.fpm.registry.services.ConfigurationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private RegistryProperties registryProperties;

    @Override
    public String getFileStoragePath() {
        return registryProperties.getFileStorage().getRoot();
    }

    @Override
    public String getRecycleBinPath() {
        return registryProperties.getFileStorage().getRecycleBin();
    }

    @Override
    public int defaultPage() {
        return registryProperties.getPagination().getDefaultPage();
    }

    @Override
    public int defaultPageSize() {
        return registryProperties.getPagination().getDefaultPageSize();
    }

    @Override
    public int maxPageSize() {
        return registryProperties.getPagination().getMaxPageSize();
    }

    @Override
    public int getEncoderStrength() {
        return registryProperties.getSecurity().getEncoderStrength();
    }

    @Override
    public Pageable getDefaultPageable() {
        return PageRequest.of(defaultPage(), defaultPageSize());
    }
}
