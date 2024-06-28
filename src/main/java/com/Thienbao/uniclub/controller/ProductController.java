package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.GetProductByCategoryRequest;
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
    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct(@RequestParam(required = false) int pageIndex,@RequestParam(required = false) int pageSize){

        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products succesfull", productServiceImp.getAll(pageIndex,pageSize));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<?> getProductsByCategory(@Valid @RequestBody  GetProductByCategoryRequest request){
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products by cateory succesfull", productServiceImp.getProductsByCategory(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertProducts(@Valid InsertProductRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Insert Product success",productServiceImp.insertProduct(request));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Get Product detail
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailProduct(@RequestParam int idProduct){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get detail Product success", productServiceImp.getDetailProduct(idProduct));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // update Product - Don't (update - name, price,description, sku)
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update Product Success", null);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // delete Product

}
