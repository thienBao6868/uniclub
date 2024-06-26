package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertSizeRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.SizeServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // insert Size
    @PostMapping("")
    public ResponseEntity<?> insertSize(@Valid @RequestBody InsertSizeRequest insertSizeRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert sizes successful",sizeServiceImp.insertSize(insertSizeRequest) );
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
