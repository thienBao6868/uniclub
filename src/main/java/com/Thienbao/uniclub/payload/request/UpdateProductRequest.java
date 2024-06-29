package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProductRequest {

    @NotNull(message = "Id product not null")
    private int idProduct;

    @NotBlank(message = "Name product not blank")
    private String name;

    @NotNull(message = "Price not null")
    private double price;

    private int rate;

    @NotBlank(message = "desc not blank")
    private String desc;

    private String sku;
}
