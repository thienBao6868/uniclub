package com.Thienbao.uniclub.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertProductRequest {
    private MultipartFile file;
    private String name;
    private double price;
    private String desc;
    private int idColor;
    private int idSize;
    private int quantity;
    private double pricePlus;
}
