package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertTagRequest {
    @NotBlank(message = "Name Tag not Blank")
    private String name;
}
