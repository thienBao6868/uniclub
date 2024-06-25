package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.CartDto;
import com.Thienbao.uniclub.model.*;
import com.Thienbao.uniclub.payload.request.CartRequest;
import com.Thienbao.uniclub.repository.CartRepository;
import com.Thienbao.uniclub.service.imp.CartServiceImp;
import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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



            Cart cartExist = cartRepository.findByUserIdAndProductIdAndColorIdAndSizeId(idUser,cartRequest.getIdProduct(), cartRequest.getIdColor(), cartRequest.getIdSize());
            if(cartExist != null){
                int oldQuantity = cartExist.getQuantity();
                cartExist.setQuantity(oldQuantity+ cartRequest.getQuantity());
                cartRepository.save(cartExist);
            }else{
                cartRepository.save(cart);
            }
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Error Insert Cart : " + ex.getMessage());
        }
    }

    @Override
    public List<CartDto> getCarts(HttpServletRequest request) {
        int idUser = jwtHelper.getIdUserFromToken(request);
        List<Cart> listCart = cartRepository.findByUserId(idUser);
        List<CartDto> listCartDto = new ArrayList<>();

        listCart.forEach(item-> {
            CartDto cartDto = new CartDto();
            cartDto.setId(item.getId());
            cartDto.setProductName(item.getProduct().getName());
            cartDto.setIdSize(item.getSize().getId());
            cartDto.setIdColor(item.getColor().getId());
            cartDto.setPrice(item.getProduct().getPrice());
            cartDto.setQuantity(item.getQuantity());
            List<ProductImage> productImageList = item.getProduct().getProductImages();
            List<String> images = new ArrayList<>();
            productImageList.forEach(image->{
                images.add("http://localhost:8080/file/"+image.getName());
            });
            cartDto.setImages(images);
            listCartDto.add(cartDto);
        });


        return listCartDto;
    }


}
