package com.fpm.registry;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "registry")
public class RegistryProperties {

    private FileStorageProperties fileStorage = new FileStorageProperties();
    private PaginationProperties pagination = new PaginationProperties();

    @Data
    public class FileStorageProperties {

        private String root;
        private String recycleBin;
    }

    @Data
    public class PaginationProperties {

        private int defaultPage;
        private int defaultPageSize;
        private int maxPageSize;
    }
}
