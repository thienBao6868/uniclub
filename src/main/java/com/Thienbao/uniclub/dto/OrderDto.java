package com.Thienbao.uniclub.dto;

import com.Thienbao.uniclub.model.STATUS_ORDER;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private UserDto user;
    private String address;
    private String phone;
    private double total;
    private LocalDateTime createDate;
    private STATUS_ORDER Status;
}
