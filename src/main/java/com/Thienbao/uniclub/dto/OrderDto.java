package com.Thienbao.uniclub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private UserDto user;
    private String address;
    private String phone;
    private double total;
    private LocalDateTime createDate;
    private String Status;
}
