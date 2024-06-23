package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertCategoryRequest {
    @NotBlank(message = "Name category not blank")
    private String name;

    private MultipartFile file;
}
