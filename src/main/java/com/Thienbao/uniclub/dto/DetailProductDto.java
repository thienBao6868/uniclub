package com.Thienbao.uniclub.dto;

import com.Thienbao.uniclub.model.Color;
import lombok.Data;

import java.util.List;

@Data
public class DetailProductDto {
    private int id;
    private String name;
    private int rate;
    private double price;
    private String description;
    private String sku;
    private List<ProductDetailDto> productDetailList;
    private List<CategoryProductDto> categoryList;
    private List<TagDto> tagList;
}
