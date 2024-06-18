package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.model.*;
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
        try{
            int idUser = jwtHelper.getIdUserFromToken(request);
            User user = new User();
            user.setId(idUser);

            Product product = new Product();
            product.setId(cartRequest.getIdProduct());

            Size size = new Size();
            size.setId(cartRequest.getIdSize());

            Color color = new Color();
            color.setId(cartRequest.getIdColor());

            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setSize(size);
            cart.setColor(color);
            cart.setQuantity(cartRequest.getQuantity());

            cartRepository.save(cart);
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Error Insert Cart : " + ex.getMessage());
        }
    }
}
