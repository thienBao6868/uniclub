package com.Thienbao.uniclub.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCartRequest {
    private List<CartItem> items;
    @Data
    public static class CartItem{
        private int idCart;
        private int idSize;
        private int idColor;
        private int quantity;
    }
}
