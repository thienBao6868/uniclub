package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.CartDto;
import com.Thienbao.uniclub.exception.DeleteException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.UpdateException;
import com.Thienbao.uniclub.model.*;
import com.Thienbao.uniclub.payload.request.CartRequest;
import com.Thienbao.uniclub.payload.request.UpdateCartRequest;
import com.Thienbao.uniclub.repository.CartRepository;
import com.Thienbao.uniclub.repository.ProductImageRepository;
import com.Thienbao.uniclub.service.imp.CartServiceImp;
import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

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
            List<ProductImage> productImageList = productImageRepository.findByProductAndColor(item.getProduct(),item.getColor());

            List<String> images = new ArrayList<>();
            productImageList.forEach(image->{
                images.add("http://localhost:8080/file/"+image.getName());
            });
            cartDto.setImages(images);
            listCartDto.add(cartDto);
        });


        return listCartDto;
    }

    @Transactional
    @Override
    public boolean updateCart(HttpServletRequest request, UpdateCartRequest updateCartRequest) {
        int idUser = jwtHelper.getIdUserFromToken(request);
        List<UpdateCartRequest.CartItem> cartItems = updateCartRequest.getItems();
        List<Cart> listCart = cartRepository.findByUserId(idUser);

        try {
            List<Cart> cartsToUpdate = new ArrayList<>();

            for (UpdateCartRequest.CartItem cartItem : cartItems) {
                Optional<Cart> cartOptional = listCart.stream()
                        .filter(item -> item.getId() == cartItem.getIdCart())
                        .findFirst();

                if (cartOptional.isPresent()) {
                    Cart cart = cartOptional.get();
                    cart.setQuantity(cartItem.getQuantity());

                    Color color = new Color();
                    color.setId(cartItem.getIdColor());
                    cart.setColor(color);

                    Size size = new Size();
                    size.setId(cartItem.getIdSize());
                    cart.setSize(size);

                    cartsToUpdate.add(cart);
                } else {
                    throw new NotFoundException("Not found Cart with id : " + cartItem.getIdCart());
                }
            }

            cartRepository.saveAll(cartsToUpdate);
            return true;

        }catch (Exception ex){
            throw  new UpdateException("Error update cart: " + ex.getMessage());
        }

    }

    @Override
    public boolean deleteCart(HttpServletRequest request, int idCart) {
        int idUser = jwtHelper.getIdUserFromToken(request);
        List<Cart> listCart = cartRepository.findByUserId(idUser);
        try{
            Optional<Cart> cartOptional = listCart.stream().filter(item -> item.getId() == idCart).findFirst();
            if (cartOptional.isPresent()){
                Cart cart = cartOptional.get();
                cartRepository.delete(cart);
            }else {
                throw new NotFoundException("Not found cart with id: " + idCart);
            }
            return true;

        }catch (Exception ex){
            throw new DeleteException("Error delete cart : " + ex.getMessage());
        }
    }


}
