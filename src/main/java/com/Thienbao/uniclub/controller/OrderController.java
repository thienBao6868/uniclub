package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.OrderRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    // Insert Order
    @PostMapping("")
    public ResponseEntity<?> insertOder(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert order Success", orderServiceImp.insertOrder(request,orderRequest));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // Get all order
    // Get order Detail
    // Update Order
    // Delete Order

}
