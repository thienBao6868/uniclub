package com.Thienbao.uniclub.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private List<String> image;
}
