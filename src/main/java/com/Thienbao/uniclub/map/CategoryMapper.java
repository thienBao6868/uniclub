package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.CategoryDto;
import com.Thienbao.uniclub.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
  public CategoryDto convertToCategoryDto(Category category){
      CategoryDto categoryDto = new CategoryDto();
      categoryDto.setId(category.getId());
      categoryDto.setName(category.getName());
      categoryDto.setImage("http://localhost:8080/file/"+category.getImage());
      return categoryDto;
  }
}
