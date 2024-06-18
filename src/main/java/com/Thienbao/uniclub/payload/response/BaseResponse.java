package com.Thienbao.uniclub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int statusCode;
    private String message;
    private Object data;
}
