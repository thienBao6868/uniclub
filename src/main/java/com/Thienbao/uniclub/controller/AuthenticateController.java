package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.SignupRequest;
import com.Thienbao.uniclub.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);

        authenticationManager.authenticate(token);

        return new ResponseEntity<>("hello login", HttpStatus.OK);
    };
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid  SignupRequest signupRequest){

        return new ResponseEntity<>(authService.insertUser(signupRequest), HttpStatus.OK);
    };


}
