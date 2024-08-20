package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetProductByNameRequest {
    private Integer pageIndex;
    private Integer pageSize;
    @NotBlank(message = "Name Product not blank")
    private String name;
}
