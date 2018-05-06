package com.fpm.registry.controllers.api;

import com.fpm.registry.facades.MediaFacade;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaApiController {

    private MediaFacade mediaFacade;

    @GetMapping("/{id}")
    public FileSystemResource getFile(@PathVariable Long id) {
        return new FileSystemResource(mediaFacade.getFile(id));
    }
}
