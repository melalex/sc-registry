package com.fpm.registry.services;

import com.fpm.registry.domain.Media;
import lombok.Value;

import java.io.File;

public interface MediaService {

    Media createMedia(String name, String type);

    Media getMediaById(Long id);

    File getFileById(Long id);

    File getFileByMedia(Media media);

    void delete(Media media);

    MediaAndFile prepareUpdate(Media media, String name, String type);

    @Value(staticConstructor = "of")
    class MediaAndFile {

        private Media media;
        private File file;
    }
}
