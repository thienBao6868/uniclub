package com.Thienbao.uniclub.service.imp;


import com.Thienbao.uniclub.dto.CartDto;
import com.Thienbao.uniclub.model.Cart;
import com.Thienbao.uniclub.payload.request.CartRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CartServiceImp {
    boolean insertCart(HttpServletRequest request, CartRequest cartRequest);

    List<CartDto> getCarts(HttpServletRequest request);

}
