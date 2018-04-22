package com.fpm.registry.controllers.view;

import static java.util.List.of;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.utils.Caches;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@AllArgsConstructor
@ViewController("/documents")
public class DocumentViewController {

    private static final String COMMIT_DOCUMENT_MESSAGE = "messages.Document.created";
    private static final String UPDATE_DOCUMENT_MESSAGE = "messages.Document.updated";

    private DocumentFacade documentFacade;

    @GetMapping("/{id}")
    @Cacheable(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView getById(@PathVariable Long id) {
        var model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_VIEW, model);
    }

    @GetMapping("/{id}/edit")
    @Cacheable(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView getByIdForEditing(@PathVariable Long id) {
        var model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_EDIT, model);
    }

    @GetMapping("/all")
    @Cacheable(Caches.ALL_DOCUMENTS)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getByNameStarts(@RequestParam(required = false) String name, Pageable pageable) {
        var model = documentFacade.getByNameStarts(name, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @GetMapping
    @Cacheable(Caches.USER_DOCUMENTS)
    public ModelAndView getByNameStartsForCurrentUser(@RequestParam(required = false) String name, Pageable pageable) {
        var model = documentFacade.getByNameStartsForCurrentUser(name, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "document.id")
    public ModelAndView update(@RequestBody @Valid DocumentDto document) {
        documentFacade.update(document);
        return Views.redirectToIndex(of(UPDATE_DOCUMENT_MESSAGE));
    }

    @PatchMapping("/{id}/commit")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict({Caches.ALL_DOCUMENTS, Caches.USER_DOCUMENTS})
    public ModelAndView commit(@PathVariable Long id) {
        documentFacade.commit(id);

        return Views.redirectToIndex(of(COMMIT_DOCUMENT_MESSAGE));
    }
}
