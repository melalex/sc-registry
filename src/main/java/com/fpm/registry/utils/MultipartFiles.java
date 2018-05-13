package com.fpm.registry.utils;

import com.fpm.registry.exceptions.UnexpectedException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class MultipartFiles {

    private static final String ERROR_MESSAGE = "Error while transferring multipart data to [ %s ]";

    public void transfer(MultipartFile source, File destination) {
        try {
            source.transferTo(destination);
        } catch (IOException e) {
            String message = String.format(ERROR_MESSAGE, destination);
            throw new UnexpectedException(message, e);
        }
    }
}
