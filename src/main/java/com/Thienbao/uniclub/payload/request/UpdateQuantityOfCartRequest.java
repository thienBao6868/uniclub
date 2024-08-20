package com.Thienbao.uniclub.payload.request;

import lombok.Data;

@Data
public class UpdateQuantityOfCartRequest {
    private int idCart;
    private int quantity;
}
