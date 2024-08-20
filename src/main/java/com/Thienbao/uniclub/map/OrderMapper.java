package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.OrderDto;
import com.Thienbao.uniclub.dto.UserDto;
import com.Thienbao.uniclub.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderDto convertToOrderDto(Orders orders){
        OrderDto orderDto = new OrderDto();

        UserDto user = new UserDto();
        user.setEmail(orders.getUser().getEmail());
        orderDto.setUser(user);
        orderDto.setAddress(orders.getAddress());
        orderDto.setPhone(orders.getPhone());
        orderDto.setTotal(orders.getTotal());
        orderDto.setCreateDate(orders.getCreateDate());
        orderDto.setStatus(orders.getStatus());
        return orderDto;
    }

}
