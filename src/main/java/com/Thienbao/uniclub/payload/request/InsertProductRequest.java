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

    private int idColor;

    private int idSize;

    private int quantity;
    
    private double pricePlus;
}
