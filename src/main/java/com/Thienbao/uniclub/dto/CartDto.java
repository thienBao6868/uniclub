package com.Thienbao.uniclub.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private int id;
    private String productName;
    private int idSize;
    private int idColor;
    private double price;
    private int quantity;
    private List<String> images;
}
