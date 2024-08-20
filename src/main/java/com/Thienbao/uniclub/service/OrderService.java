package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.OrderDetailDto;
import com.Thienbao.uniclub.dto.OrderDto;
import com.Thienbao.uniclub.exception.InsertOrderException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.UpdateException;
import com.Thienbao.uniclub.map.OrderDetailMapper;
import com.Thienbao.uniclub.map.OrderMapper;
import com.Thienbao.uniclub.model.*;
import com.Thienbao.uniclub.model.key.OrderDetailID;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import com.Thienbao.uniclub.payload.request.OrderRequest;
import com.Thienbao.uniclub.payload.request.UpdateOrderRequest;
import com.Thienbao.uniclub.repository.CartRepository;
import com.Thienbao.uniclub.repository.OrderDetailRepository;
import com.Thienbao.uniclub.repository.OrderRepository;
import com.Thienbao.uniclub.repository.ProductDetailRepository;
import com.Thienbao.uniclub.service.imp.OrderServiceImp;
import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Transactional
    @Override
    public boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest) {

        try{
            int idUser = jwtHelper.getIdUserFromToken(request);
            Orders order = new Orders();
            order.setPhone(orderRequest.getPhone());
            order.setAddress(orderRequest.getAddress());
            order.setTotal(orderRequest.getTotal());
            order.setCreateDate(LocalDateTime.now());

            User user = new User();
            user.setId(idUser);
            order.setUser(user);
            orderRepository.save(order);

            List<Cart> carts = cartRepository.findByUserId(idUser);
            if(carts.isEmpty()) throw new NotFoundException("User needs to add products to cart");
            carts.forEach(item->{
                OrderDetail orderDetail = new OrderDetail();
                OrderDetailID orderDetailID = new OrderDetailID();
                orderDetailID.setIdOrder(order.getId());
                orderDetailID.setIdProduct(item.getProduct().getId());
                orderDetailID.setIdColor(item.getColor().getId());
                orderDetailID.setIdSize(item.getSize().getId());

                orderDetail.setOrderDetailID(orderDetailID);
                orderDetail.setPrice(item.getProduct().getPrice());
                orderDetail.setQuantity(item.getQuantity());
                orderDetailRepository.save(orderDetail);


                // Subtract product quantity
                ProductDetailID productDetailID = new ProductDetailID();
                productDetailID.setIdProduct(item.getProduct().getId());
                productDetailID.setIdColor(item.getColor().getId());
                productDetailID.setIdSize(item.getSize().getId());

                ProductDetail productDetail = productDetailRepository.findById(productDetailID).orElseThrow(()-> new NotFoundException("Not found Product detail with id"));

                if(item.getQuantity() > productDetail.getQuantity()) throw new InsertOrderException("The number of products ordered cannot be greater than the number of products in stock");
                int newQuantity = productDetail.getQuantity() - item.getQuantity();
                productDetail.setQuantity(newQuantity);
                productDetailRepository.save(productDetail);

                // Delete Data in Cart Database.
                cartRepository.delete(item);
            });

            return true;
        }catch (Exception ex){
            throw new InsertOrderException("Error insert Order: " + ex.getMessage());
        }

    }

    @Override
    public List<OrderDto> getAll() {
        List<Orders> ordersList = orderRepository.findAll();
        return ordersList.stream().map(orderMapper::convertToOrderDto).toList();
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetail(int idOrder) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(idOrder);

        if (orderDetails.isEmpty()) throw  new NotFoundException("Not found Orders with id order : " + idOrder);

        return orderDetails.stream().map(orderDetailMapper::convertToOrderDetailDto).toList();

    }

    @Override
    public boolean updateOrder(UpdateOrderRequest updateOrderRequest) {
        Orders order = orderRepository.findById(updateOrderRequest.getIdOrder()).orElseThrow(()-> new NotFoundException("Not found order with order Id: " + updateOrderRequest.getIdOrder()));
        try {
            order.setStatus(updateOrderRequest.getStatus());
            orderRepository.save(order);
            return true;
        }catch (Exception ex){
            throw  new UpdateException("Error update order : " + ex.getMessage());
        }
    };




}
