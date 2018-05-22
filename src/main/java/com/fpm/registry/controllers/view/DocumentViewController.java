package com.fpm.registry.controllers.view;

import static java.util.List.of;

import com.fpm.registry.dto.DocumentDto;
import com.fpm.registry.dto.DocumentRequest;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.services.I18nService;
import com.fpm.registry.utils.Caches;
import com.fpm.registry.utils.Messages;
import com.fpm.registry.utils.Urls;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentViewController {

    private DocumentFacade documentFacade;
    private I18nService i18nService;

    @GetMapping
    public ModelAndView getByNameContains(DocumentRequest request, Pageable pageable) {
        Page<DocumentDto> model = documentFacade.getByCodeAndDateRange(request, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @GetMapping("/{id:\\d+}")
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

    @PostMapping("/{id}/delete")
    @Cacheable(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView deleteById(@PathVariable Long id, Map<String, Object> params) {
        documentFacade.delete(id);
        return Views.redirectTo(Urls.INDEX, params);
    }

    @PatchMapping("/{id}/commit")
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = Caches.SINGLE_DOCUMENT, key = "id")
    public ModelAndView commit(@PathVariable Long id, Locale locale) {
        documentFacade.commit(id);

        return Views.redirectToIndex(of(i18nService.getMessage(Messages.COMMIT_DOCUMENT_MESSAGE, locale)));
    }
}
