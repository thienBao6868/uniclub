package com.Thienbao.uniclub.controller;

import com.Thienbao.uniclub.payload.request.SignupRequest;
import com.Thienbao.uniclub.payload.response.BaseResponse;
import com.Thienbao.uniclub.service.AuthService;
import com.Thienbao.uniclub.utils.JwtHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password){

//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("Kiem tra " + key);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);

        authenticationManager.authenticate(token);
        // code chay khi dang nhap thanh cong

        String jwtToken = jwtHelper.generateToken("Hello jwt");

        BaseResponse baseResponse = new BaseResponse();

        baseResponse.setStatusCode(200);
        baseResponse.setMessage("success");
        baseResponse.setData(jwtToken);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    };
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid  SignupRequest signupRequest){

        return new ResponseEntity<>(authService.insertUser(signupRequest), HttpStatus.OK);
    };


}
