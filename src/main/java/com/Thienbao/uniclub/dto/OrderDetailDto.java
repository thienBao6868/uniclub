package com.Thienbao.uniclub.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private OrderDto order;
    private ProductDto product;
    private SizeDto sizeDto;
    private ColorDto colorDto;
    private int quantity;
    private double price;
}
