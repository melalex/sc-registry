package com.fpm.registry.controllers.view;

import static java.util.List.of;

import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.services.I18nService;
import com.fpm.registry.utils.Caches;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentViewController {

    private static final String COMMIT_DOCUMENT_MESSAGE = "messages.document.created";

    private DocumentFacade documentFacade;
    private I18nService i18nService;

    @GetMapping
    public ModelAndView getByNameContains(@RequestParam(required = false) String name, Pageable pageable) {
        Page<DocumentDto> model = documentFacade.getByNameContains(name, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @GetMapping("/{id}")
    @Cacheable(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView getById(@PathVariable Long id) {
        DocumentDto model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_VIEW, model);
    }

    @GetMapping("/{id}/edit")
    @Cacheable(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView getByIdForEditing(@PathVariable Long id) {
        DocumentDto model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_EDIT, model);
    }

    @PatchMapping("/{id}/commit")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView commit(@PathVariable Long id, Locale locale) {
        documentFacade.commit(id);

        return Views.redirectToIndex(of(i18nService.getMessage(COMMIT_DOCUMENT_MESSAGE, locale)));
    }
}
