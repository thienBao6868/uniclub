package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.OrderRequest;
import com.Thienbao.uniclub.payload.request.UpdateOrderRequest;
import com.Thienbao.uniclub.payload.request.UpdateProductRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "get all order Successful",orderServiceImp.getAll());
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    // Get order Detail
    @GetMapping("/{idOrder}")
    public ResponseEntity<?> getDetailOrder(@PathVariable int idOrder){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "get order detail Successful",orderServiceImp.getAllOrderDetail(idOrder));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    // Update Order
    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody UpdateOrderRequest updateOrderRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update order Successful",orderServiceImp.updateOrder(updateOrderRequest));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    // Delete Order

}
