package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.CategoryDto;
import com.Thienbao.uniclub.payload.request.InsertCategoryRequest;

import java.util.List;

public interface CategoryServiceImp {

    List<CategoryDto> getAll();
    boolean insertCategory(InsertCategoryRequest insertCategoryRequest);

}
