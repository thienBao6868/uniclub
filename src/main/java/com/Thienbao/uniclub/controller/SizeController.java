package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeServiceImp sizeServiceImp;

    // get all Size
    @GetMapping("/all")
    public ResponseEntity<?> getSizes(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get sizes successful",sizeServiceImp.getSizes() );
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
