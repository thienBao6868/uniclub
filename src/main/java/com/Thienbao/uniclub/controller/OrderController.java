package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.OrderRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> insertOder(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert order Success", null);
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
