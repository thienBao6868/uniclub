package com.Thienbao.uniclub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<>("Hello all product", HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertProducts(){
        return  new ResponseEntity<>("insert product", HttpStatus.OK);
    }
}
