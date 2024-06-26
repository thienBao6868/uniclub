package com.Thienbao.uniclub.dto;

import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.model.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDto {
    private ColorDto color;
    private SizeDto size;
    private int quantity;
    private double pricePlus;

}
