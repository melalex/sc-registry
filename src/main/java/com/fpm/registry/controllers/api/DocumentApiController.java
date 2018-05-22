package com.fpm.registry.controllers.api;

import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.dto.MediaDto;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.forms.DocumentForm;
import com.fpm.registry.utils.Caches;
import com.fpm.registry.utils.MultipartFiles;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentApiController {

    private DocumentFacade documentFacade;

    @PostMapping
    @CacheEvict(Caches.TAGS)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER')")
    public DocumentDto create(@RequestBody @Valid DocumentForm document) {
        return documentFacade.create(document);
    }

    @PatchMapping("/{id:\\d+}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "id")
    public DocumentDto update(@Valid @RequestBody DocumentForm document, @PathVariable long id) {
        return documentFacade.update(document, id);
    }

    @DeleteMapping
    public void rollback(Long id) {
        documentFacade.rollback(id);
    }

    @PatchMapping("/{id}/media")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "id")
    public MediaDto updateAttachment(@PathVariable Long id, MultipartFile attachment) {
        File destination =
                documentFacade.updateAttachment(id, attachment.getOriginalFilename(), attachment.getContentType());

        MultipartFiles.transfer(attachment, destination);

        return documentFacade.getById(id).getAttachment();
    }
}
