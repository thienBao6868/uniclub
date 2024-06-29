package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertProductDetailRequest {
    private MultipartFile[] files;

    @NotNull(message = "Id product not null")
    private int idProduct;

    @NotNull(message = "Id color not null")
    private int idColor;

    @NotNull(message = "Id Size not null")
    private int idSize;

    @NotNull(message = "quantity not null")
    private int quantity;

    private double pricePlus;

}
