package com.fpm.registry.controllers.view;

import static java.util.List.of;

import com.fpm.registry.annotations.ViewController;
import com.fpm.registry.facades.DocumentFacade;
import com.fpm.registry.utils.Views;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@ViewController("/documents")
public class DocumentViewController {

    private static final String COMMIT_DOCUMENT_MESSAGE = "messages.document.created";

    private DocumentFacade documentFacade;

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        var model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_VIEW, model);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getByIdForEditing(@PathVariable Long id) {
        var model = documentFacade.getById(id);
        return Views.from(Views.DOCUMENT_EDIT, model);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getByNameStarts(@RequestParam(required = false) String name, Pageable pageable) {
        var model = documentFacade.getByNameStarts(name, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @GetMapping
    public ModelAndView getByNameStartsForCurrentUser(@RequestParam(required = false) String name, Pageable pageable) {
        var model = documentFacade.getByNameStartsForCurrentUser(name, pageable);
        return Views.from(Views.DOCUMENT_LIST, model);
    }

    @PatchMapping("/{id}/commit")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView commit(@PathVariable Long id) {
        documentFacade.commit(id);

        return Views.redirectToIndex(of(COMMIT_DOCUMENT_MESSAGE));
    }
}
