package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import jakarta.validation.Valid;
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
        return new ResponseEntity<>(productServiceImp.getAll(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertProducts(@Valid InsertProductRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Insert Product success",productServiceImp.insertProduct(request));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Get Product detail
    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getDetailProduct(@PathVariable int idProduct){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get detail Product success", productServiceImp.getDetailProduct(idProduct));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // update Product
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update Product Success", null);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // delete Product

}
