package com.Thienbao.uniclub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlreadyExistException extends RuntimeException {
    private String message;
}
