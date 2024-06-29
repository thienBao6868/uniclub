package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertProductDetailRequest;
import com.Thienbao.uniclub.payload.request.UpdateProductDetailRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.ProductDetailServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailServiceImp productDetailServiceImp;

    // insert new product detail
    @PostMapping("")
    public ResponseEntity<?> insertProductDetail(@Valid InsertProductDetailRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert product detail successful", productDetailServiceImp.insertProductDetail(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // update product detail
    @PutMapping("")
    public ResponseEntity<?> updateProductDetail(@Valid @RequestBody UpdateProductDetailRequest updateProductDetailRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update product detail successful",productDetailServiceImp.updateProductDetail(updateProductDetailRequest));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // delete Product detail



}
