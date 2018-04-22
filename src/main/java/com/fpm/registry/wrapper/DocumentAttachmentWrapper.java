package com.fpm.registry.wrapper;

import com.fpm.registry.constraints.ValidMultipartFile;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentAttachmentWrapper {

    @ValidMultipartFile(types = MediaType.APPLICATION_PDF_VALUE)
    private MultipartFile attachment;
}
