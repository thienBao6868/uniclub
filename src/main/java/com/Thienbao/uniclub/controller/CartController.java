package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.CartRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping("")
    public ResponseEntity<?> insertToCart(@RequestBody CartRequest cartRequest){
        return new ResponseEntity<>(cartRequest, HttpStatus.OK);
    }
}
