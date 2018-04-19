package com.fpm.registry.services;

import com.fpm.registry.domain.Media;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    Media save(MultipartFile multipartFile);

    Resource getByName(String name);
}
