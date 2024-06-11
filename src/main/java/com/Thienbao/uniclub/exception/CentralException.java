package com.Thienbao.uniclub.exception;

import com.Thienbao.uniclub.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {

    @ExceptionHandler({RuntimeException.class, InsertProductException.class, SaveFileException.class})
    public ResponseEntity handleException(Exception e){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setMessage(e.getMessage());
        baseResponse.setData("");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
