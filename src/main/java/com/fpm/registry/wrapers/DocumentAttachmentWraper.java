package com.fpm.registry.wrapers;

import com.fpm.registry.constraints.ValidMultipartFile;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DocumentAttachmentWraper {

    @ValidMultipartFile(types = MediaType.APPLICATION_PDF_VALUE)
    private MultipartFile attachment;
}
