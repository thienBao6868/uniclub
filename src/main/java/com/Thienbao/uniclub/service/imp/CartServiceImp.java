package com.Thienbao.uniclub.service.imp;


import com.Thienbao.uniclub.dto.CartDto;
import com.Thienbao.uniclub.model.Cart;
import com.Thienbao.uniclub.payload.request.CartRequest;
import com.Thienbao.uniclub.payload.request.UpdateCartRequest;
import com.Thienbao.uniclub.payload.request.UpdateQuantityOfCartRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CartServiceImp {
    boolean insertCart(HttpServletRequest request, CartRequest cartRequest);

    List<CartDto> getCarts(HttpServletRequest request);

    boolean updateCart(HttpServletRequest request, UpdateCartRequest updateCartRequest);

    boolean updateQuantityOfCart(HttpServletRequest request, UpdateQuantityOfCartRequest updateQuantityOfCartRequest);

    boolean deleteCart(HttpServletRequest request, int idCart);



}
