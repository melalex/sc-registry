package com.fpm.registry.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class MultipartFiles {

    private static final String ERROR_MESSAGE = "Error while transferring multipart data";

    public void transfer(MultipartFile source, File destination) {
        try {
            source.transferTo(destination);
        } catch (IOException e) {
            throw new UnsupportedOperationException(ERROR_MESSAGE, e);
        }
    }
}
