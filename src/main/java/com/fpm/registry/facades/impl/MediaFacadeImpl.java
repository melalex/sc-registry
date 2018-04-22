package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.facades.MediaFacade;
import com.fpm.registry.services.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Facade
@AllArgsConstructor
public class MediaFacadeImpl implements MediaFacade {

    private MediaService mediaService;

    @Override
    @Transactional(readOnly = true)
    public File getFile(Long id) {
        return mediaService.getFileById(id);
    }
}
