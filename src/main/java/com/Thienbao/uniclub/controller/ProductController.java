package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.*;
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
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products successful", productServiceImp.getAll(pageIndex,pageSize));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<?> getProductsByCategory(@Valid @RequestBody  GetProductByCategoryRequest request){
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products by category successful", productServiceImp.getProductsByCategory(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-tag")
    public ResponseEntity<?> getProductsByTag(@Valid @RequestBody GetProductByTagRequest request){
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products by tag successful", productServiceImp.getProductsByTag(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-price")
    public ResponseEntity<?> getProductsByPrice(@Valid @RequestBody GetProductByPriceRequest request){
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products by price successful", productServiceImp.getProductByPrice(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-name")
    public ResponseEntity<?> getProductsByName(@Valid @RequestBody GetProductByNameRequest request){
        BaseResponse  baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get Products by price successful", productServiceImp.getProductByName(request));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @GetMapping("/detail")
    public ResponseEntity<?> getDetailProduct(@RequestParam int idProduct){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get detail Product success", productServiceImp.getDetailProduct(idProduct));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> insertProducts(@Valid InsertProductRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Insert Product success",productServiceImp.insertProduct(request));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Get Product detail


    // update Product
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequest updateProductRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update Product Successful", productServiceImp.updateProduct(updateProductRequest));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    // delete Product

}
