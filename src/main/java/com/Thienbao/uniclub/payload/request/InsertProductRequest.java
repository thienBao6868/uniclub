package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertProductRequest {

    private MultipartFile[] files;

    @NotBlank(message = "Name Product not blank")
    private String name;

    @NotNull(message = "Price not null")
    private double price;

    @NotBlank(message = "Description not blank")
    private String desc;

    @NotNull(message = "Id color not null")
    private int idColor;

    @NotNull(message = "Id Size not null")
    private int idSize;

    @NotNull(message = "Id category not null")
    private int idCategory;

    @NotNull(message = "Id Tag not null")
    private int idTag;

    @NotNull(message = "quantity not null")
    private int quantity;

    private String sku;

    private double pricePlus;
}
