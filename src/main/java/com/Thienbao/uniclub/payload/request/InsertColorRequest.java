package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertColorRequest {
    @NotBlank(message = "Name color not blank")
    private String name;
}
