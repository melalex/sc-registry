package com.fpm.registry.facades.impl;

import com.fpm.registry.annotations.Facade;
import com.fpm.registry.dto.MediaDto;
import com.fpm.registry.dto.MediaFileVo;
import com.fpm.registry.facades.MediaFacade;
import com.fpm.registry.services.MediaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Facade
@AllArgsConstructor
public class MediaFacadeImpl implements MediaFacade {

    private MediaService mediaService;
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public MediaFileVo getMediaFile(Long id) {
        File file = mediaService.getFileById(id);
        MediaDto media = modelMapper.map(mediaService.getMediaById(id), MediaDto.class);

        return MediaFileVo.of(media, file);
    }
}
