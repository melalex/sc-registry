package com.fpm.registry.controllers.api;

import com.fpm.registry.dto.MediaFileVo;
import com.fpm.registry.facades.MediaFacade;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Resource>  getFile(@PathVariable Long id) {
        MediaFileVo mediaFile = mediaFacade.getMediaFile(id);

        return ResponseEntity.ok()
                .contentLength(mediaFile.length())
                .contentType(MediaType.parseMediaType(mediaFile.getMedia().getType()))
                .body(mediaFile.getByteArrayResource());
    }
}
