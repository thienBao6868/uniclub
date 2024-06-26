package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorServiceImp colorServiceImp;

    // get all color
    @GetMapping("/all")
    public ResponseEntity<?> getColors(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get colors successful", colorServiceImp.getColors());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


}
