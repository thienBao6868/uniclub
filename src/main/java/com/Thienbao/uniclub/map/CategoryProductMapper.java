package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.CategoryProductDto;
import com.Thienbao.uniclub.model.CategoryProduct;

import org.springframework.stereotype.Service;

@Service
public class CategoryProductMapper {

    public CategoryProductDto convertToCategoryProductDto(CategoryProduct categoryProduct){
        CategoryProductDto categoryProductDto = new CategoryProductDto();
        categoryProductDto.setId(categoryProduct.getCategory().getId());
        categoryProductDto.setName(categoryProduct.getCategory().getName());
        categoryProductDto.setImage(categoryProduct.getCategory().getImage());
        return categoryProductDto;
    }
}
