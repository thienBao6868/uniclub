package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.InsertTagRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.imp.TagServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagServiceImp tagServiceImp;
    // get All tag
    @GetMapping("/all")
    public ResponseEntity<?> getTags(){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "get all tag success full",tagServiceImp.getTags());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // insert tag
    @PostMapping("")
    public ResponseEntity<?> insertTag(@Valid @RequestBody InsertTagRequest insertTagRequest){
        BaseResponse baseResponse = new BaseResponse(HttpStatus.OK.value(), "insert tag success full",tagServiceImp.insertTag(insertTagRequest));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    // update tag

    // deleted tag
}
