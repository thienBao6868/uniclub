package com.Thienbao.uniclub.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {

    @NotNull(message = "Email can't null")
    @NotBlank(message = "Email not blank")
    @Email(message = "Invalid Email")
    private String email;


    @NotNull(message = "Password not null")
    @NotBlank(message = "Password not blank")
    private String password;
}
