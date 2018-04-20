package com.fpm.registry.services.impl;

import com.fpm.registry.domain.Media;
import com.fpm.registry.repositories.MediaRepository;
import com.fpm.registry.services.ConfigurationService;
import com.fpm.registry.services.MediaService;
import com.fpm.registry.services.strategy.NamingStrategy;
import com.fpm.registry.utils.Exceptions;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private static final String INIT_MESSAGE =
            "Media service initialized successfully. File storage path is [{}], recycle bin path is [{}]";
    private static final String DELETE_MESSAGE = "Deleting media [[{}]. Moved from [{}] to [{}]";
    private static final String NEW_MEDIA_MESSAGE = "Created new Media: {}";

    private MediaRepository mediaRepository;
    private NamingStrategy namingStrategy;
    private ConfigurationService configurationService;

    private Path fileStorage;
    private Path recycleBin;

    @SneakyThrows
    @PostConstruct
    public void init() {
        fileStorage = Paths.get(configurationService.getFileStoragePath());
        recycleBin = Paths.get(configurationService.getRecycleBinPath());

        Files.createDirectories(fileStorage);
        Files.createDirectories(recycleBin);

        log.info(INIT_MESSAGE, fileStorage, recycleBin);
    }

    @Override
    public Media createMedia(String name, String type) {
        var media = new Media();
        var fileName = namingStrategy.provideName(name);
        var path = fileStorage.resolve(fileName).toString();

        media.setName(fileName);
        media.setStatus(Media.Status.ACTIVE);
        media.setType(type);
        media.setOriginalName(name);
        media.setPath(path);

        return media;
    }

    @Override
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(Exceptions.notFoundSupplier(Media.class, id));
    }

    @Override
    public File getFileById(Long id) {
        return getFileByMedia(getMediaById(id));
    }

    @Override
    public File getFileByMedia(Media media) {
        return new File(media.getPath());
    }

    @Override
    @SneakyThrows
    public void delete(Media media) {
        var newPath = recycleBin.resolve(media.getName());
        var oldPath = Paths.get(media.getPath());

        media.setPath(newPath.toString());
        media.setStatus(Media.Status.DELETED);

        Files.move(oldPath, newPath);

        log.info(DELETE_MESSAGE, media.getId(), oldPath, newPath);

        mediaRepository.save(media);
    }

    @Override
    public MediaAndFile prepareUpdate(Media media, String name, String type) {
        var newMedia = createMedia(name, type);

        log.info(NEW_MEDIA_MESSAGE, media);

        if (Objects.nonNull(media)) {
            delete(media);
        }

        return MediaAndFile.of(newMedia, getFileByMedia(newMedia));
    }
}
