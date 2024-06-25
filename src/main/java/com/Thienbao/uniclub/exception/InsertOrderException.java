package com.Thienbao.uniclub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertOrderException extends RuntimeException{
    private String message;
}
