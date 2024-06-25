package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.CategoryDto;
import com.Thienbao.uniclub.exception.AlreadyExistException;
import com.Thienbao.uniclub.exception.InsertCategoryException;
import com.Thienbao.uniclub.exception.SaveFileException;
import com.Thienbao.uniclub.model.Category;
import com.Thienbao.uniclub.payload.request.InsertCategoryRequest;
import com.Thienbao.uniclub.repository.CategoryRepository;
import com.Thienbao.uniclub.service.imp.CategoryServiceImp;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileServiceImp fileServiceImp;

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(item -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(item.getId());
            categoryDto.setName(item.getName());
            categoryDto.setImage("http://localhost:8080/file/"+item.getImage());
            categoryDtoList.add(categoryDto);
        });
        return categoryDtoList;
    }

    @Override
    public boolean insertCategory(InsertCategoryRequest insertCategoryRequest) {

        if (categoryRepository.findByName(insertCategoryRequest.getName()) != null) throw new AlreadyExistException("Category already exist");
        try{
            boolean isCopySuccess = fileServiceImp.saveFile(insertCategoryRequest.getFile());
            if(isCopySuccess){
                Category category = new Category();
                category.setName(insertCategoryRequest.getName());
                category.setImage(insertCategoryRequest.getFile().getOriginalFilename());

                categoryRepository.save(category);
                return true;
            }else {
                throw  new SaveFileException("Error save  file");
            }
        }catch (Exception e){
            throw new InsertCategoryException("Error insert category");
        }

    }



}
