package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertProductDetailRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.ProductDetailServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // delete Product detail

}
