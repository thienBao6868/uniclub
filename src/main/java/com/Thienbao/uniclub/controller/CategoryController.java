package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    // Get all category
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "get all category success", categoryServiceImp.getAll());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // Insert category
    // Update category
    // Delete category

}
