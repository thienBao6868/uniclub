package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.model.Cart;
import com.Thienbao.uniclub.model.OrderDetail;
import com.Thienbao.uniclub.model.Orders;
import com.Thienbao.uniclub.model.User;
import com.Thienbao.uniclub.model.key.OrderDetailID;
import com.Thienbao.uniclub.payload.request.OrderRequest;
import com.Thienbao.uniclub.repository.CartRepository;
import com.Thienbao.uniclub.repository.OrderDetailRepository;
import com.Thienbao.uniclub.repository.OrderRepository;
import com.Thienbao.uniclub.service.imp.OrderServiceImp;
import com.Thienbao.uniclub.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private JwtHelper jwtHelper;

    @Transactional
    @Override
    public boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest) {
        int idUser = jwtHelper.getIdUserFromToken(request);
        Orders order = new Orders();
        order.setPhone(orderRequest.getPhone());
        order.setAddress(orderRequest.getAddress());
        order.setTotal(orderRequest.getTotal());
        User user = new User();
        user.setId(idUser);
        order.setUser(user);
        orderRepository.save(order);

        List<Cart> carts = cartRepository.findByUserId(idUser);
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

            // Delete Data in Cart Database.
        });

        return false;
    }
}
