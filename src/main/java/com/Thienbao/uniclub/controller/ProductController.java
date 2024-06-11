package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductServiceImp productServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<>("Hello all product", HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertProducts(InsertProductRequest request){

        productServiceImp.insertProduct(request);

        return  new ResponseEntity<>("insert product", HttpStatus.OK);
    }
}
