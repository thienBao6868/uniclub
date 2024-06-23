package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.CategoryDto;
import com.Thienbao.uniclub.model.Category;
import com.Thienbao.uniclub.repository.CategoryRepository;
import com.Thienbao.uniclub.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(item -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(item.getId());
            categoryDto.setName(item.getName());
            categoryDto.setImage(item.getImage());
            categoryDtoList.add(categoryDto);
        });
        return categoryDtoList;
    }
}
