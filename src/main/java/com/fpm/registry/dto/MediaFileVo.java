package com.fpm.registry.dto;

import com.fpm.registry.exceptions.UnexpectedException;
import lombok.Value;
import org.springframework.core.io.ByteArrayResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Value(staticConstructor = "of")
public class MediaFileVo {

    private static final String ERROR_MESSAGE = "IO error during [ %s ] reading";

    private MediaDto media;
    private File file;

    public ByteArrayResource getByteArrayResource() {
        Path path = Paths.get(file.getAbsolutePath());
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            String message = String.format(ERROR_MESSAGE, path.toAbsolutePath());
            throw new UnexpectedException(message, e);
        }
    }

    public long length() {
        return file.length();
    }
}
