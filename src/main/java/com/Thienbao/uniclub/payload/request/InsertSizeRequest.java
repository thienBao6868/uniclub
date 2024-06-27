package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertSizeRequest {

    @NotBlank(message = "Name Size not Blank")
    private String name;
}
