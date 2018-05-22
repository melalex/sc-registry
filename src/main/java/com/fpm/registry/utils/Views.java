package com.fpm.registry.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@UtilityClass
public class Views {

    public static final String NOT_FOUND = "errors/notFound";
    public static final String INTERNAL_SERVER_ERROR = "errors/internalServerError";
    
    public static final String DOCUMENT_LIST = "documentList";
    public static final String DOCUMENT_VIEW = "viewDocument";
    public static final String DOCUMENT_EDIT = "editDocument";
    public static final String SIGN_IN = "signIn";
    public static final String SIGN_UP = "signUp";

    private static final String MODEL_ATTRIBUTE = "model";
    private static final String MESSAGES_ATTRIBUTE = "messages";
    private static final String REDIRECT_FORMAT = "redirect:%s";

    public ModelAndView from(String view, Object model) {
        return new ModelAndView(view, MODEL_ATTRIBUTE, model);
    }

    public ModelAndView from(String view) {
        return new ModelAndView(view);
    }

    public String redirectTo(String url) {
        return String.format(REDIRECT_FORMAT, url);
    }

    public ModelAndView redirectToIndex() {
        return redirectToIndex(List.of());
    }

    public ModelAndView redirectToIndex(List<String> messages) {
        return redirectTo(Urls.INDEX, messages);
    }

    public ModelAndView redirectTo(String view, List<String> messages) {
        return redirectTo(view, Map.of(MESSAGES_ATTRIBUTE, messages));
    }

    public ModelAndView redirectTo(String view, Map<String, Object> model) {
        return new ModelAndView(redirectTo(view), model);
    }
}
