package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.CartRequest;
import com.Thienbao.uniclub.payload.request.UpdateCartRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.CartServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImp cartServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertToCart(HttpServletRequest request, @RequestBody CartRequest cartRequest){
        boolean isSuccess = cartServiceImp.insertCart(request,cartRequest);
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Insert Cart Successful", isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getCarts(HttpServletRequest request){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get carts successful", cartServiceImp.getCarts(request));
        return  new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Update cart
    @PutMapping("")
    public ResponseEntity<?> updateToCart(HttpServletRequest request, @RequestBody UpdateCartRequest updateCartRequest ){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "update Cart Successful", cartServiceImp.updateCart(request,updateCartRequest));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Delete Cart
    @DeleteMapping("/{idCart}")
    public ResponseEntity<?> deleteCart(HttpServletRequest request, @PathVariable int idCart){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Deleted Cart Successful", cartServiceImp.deleteCart(request,idCart));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
