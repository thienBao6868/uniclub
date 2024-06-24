package com.Thienbao.uniclub.payload.request;

import lombok.Data;

@Data
public class OrderRequest {

    private String address;
    private String phone;
    private double total;
}
