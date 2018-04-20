package com.fpm.registry.services.impl;

import com.fpm.registry.RegistryProperties;
import com.fpm.registry.services.ConfigurationService;
import lombok.AllArgsConstructor;
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
}
