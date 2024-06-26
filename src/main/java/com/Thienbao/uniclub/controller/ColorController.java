package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertColorRequest;
import com.Thienbao.uniclub.payload.request.InsertSizeRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.ColorServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorServiceImp colorServiceImp;

    // get all color
    @GetMapping("/all")
    public ResponseEntity<?> getColors() {
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "Get colors successful", colorServiceImp.getColors());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // Insert Color
    @PostMapping("")
    public ResponseEntity<?> insertSize(@Valid @RequestBody InsertColorRequest insertColorRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert color successful",colorServiceImp.insertColor(insertColorRequest));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
