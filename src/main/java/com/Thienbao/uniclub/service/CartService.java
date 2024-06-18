package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.payload.request.CartRequest;
import com.Thienbao.uniclub.repository.CartRepository;
import com.Thienbao.uniclub.service.imp.CartServiceImp;
import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CartRepository cartRepository;
    @Override
    public boolean insertCart(HttpServletRequest request, CartRequest cartRequest) {
        int userId = jwtHelper.getIdUserFromToken(request);


        return false;
    }
}
