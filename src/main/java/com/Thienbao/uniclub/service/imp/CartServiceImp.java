package com.Thienbao.uniclub.service.imp;


import com.Thienbao.uniclub.payload.request.CartRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface CartServiceImp {
    boolean insertCart(HttpServletRequest request, CartRequest cartRequest);
}
