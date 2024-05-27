package com.Thienbao.uniclub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenController {
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(){
        return new ResponseEntity<>("hello login", HttpStatus.OK);
    };
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(){
        return new ResponseEntity<>("Hello signUp", HttpStatus.OK);
    };


}
