package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetProductByTagRequest {
    @NotNull(message = "ID tag not null")
    private int idTag;
    private Integer pageIndex;
    private Integer pageSize;
}
