package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetProductByCategoryRequest {

    @NotNull(message = "ID category not null")
    private int idCategory;
    private Integer pageIndex;
    private Integer pageSize;

}
