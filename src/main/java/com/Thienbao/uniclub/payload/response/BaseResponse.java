package com.Thienbao.uniclub.payload.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BaseResponse {
    private int statusCode;
    private String message;
    private Object data;
}
