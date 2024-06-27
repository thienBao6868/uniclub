package com.Thienbao.uniclub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertColorException extends RuntimeException{
    private String message;
}
