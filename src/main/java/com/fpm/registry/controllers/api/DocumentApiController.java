package com.fpm.registry.controllers.api;

import com.fpm.registry.annotations.ApiController;
import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.dto.MediaDto;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.forms.DocumentForm;
import com.fpm.registry.utils.Caches;
import com.fpm.registry.utils.MultipartFiles;
import com.fpm.registry.wrapper.DocumentAttachmentWrapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@AllArgsConstructor
@ApiController("/api/v1")
public class DocumentApiController {

    private DocumentFacade documentFacade;

    @PostMapping
    @CacheEvict(Caches.TAGS)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_USER')")
    public DocumentDto create(@RequestBody @Valid DocumentForm document) {
        return documentFacade.create(document);
    }

    @PatchMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "document.id")
    public DocumentDto update(@RequestBody @Valid DocumentDto document) {
        return documentFacade.update(document);
    }

    @DeleteMapping
    public void rollback(Long id) {
        documentFacade.rollback(id);
    }

    @PatchMapping("/{id}/media")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "id")
    public MediaDto updateAttachment(@PathVariable Long id, @Valid DocumentAttachmentWrapper attachment) {
        var file = attachment.getAttachment();
        var destination = documentFacade.updateAttachment(id, file.getOriginalFilename(), file.getContentType());

        MultipartFiles.transfer(file, destination);

        return documentFacade.getById(id).getAttachment();
    }
}
