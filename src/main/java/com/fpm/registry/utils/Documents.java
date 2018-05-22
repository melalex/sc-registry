package com.fpm.registry.utils;

import com.fpm.registry.domain.Document;
import com.fpm.registry.domain.Media;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class Documents {

    public void linkWithMedia(Document document) {
        linkWithMedia(document, document.getAttachment());
    }

    public void linkWithMedia(Document document, Media media) {
        if (Objects.nonNull(document) && Objects.nonNull(media)) {
            document.setAttachment(media);
            media.setDocument(document);
        }
    }
}
