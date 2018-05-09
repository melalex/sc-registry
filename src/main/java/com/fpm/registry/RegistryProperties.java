package com.fpm.registry;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "registry")
public class RegistryProperties {

    private SecurityProperties securityProperties;
    private FileStorageProperties fileStorage;
    private PaginationProperties pagination;

    @Data
    public static class SecurityProperties {

        private int encoderStrength;
    }

    @Data
    public static class FileStorageProperties {

        private String root;
        private String recycleBin;
    }

    @Data
    public static class PaginationProperties {

        private int defaultPage;
        private int defaultPageSize;
        private int maxPageSize;
    }
}
