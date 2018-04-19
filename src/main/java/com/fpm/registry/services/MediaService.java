package com.fpm.registry.services;

import com.fpm.registry.domain.Media;

import java.io.File;

public interface MediaService {

    Media getMedia(String name, String type);

    File getFile(Long id);
}
