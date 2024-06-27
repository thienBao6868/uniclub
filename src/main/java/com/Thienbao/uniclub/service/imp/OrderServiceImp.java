package com.Thienbao.uniclub.service.imp;


import com.Thienbao.uniclub.dto.OrderDto;
import com.Thienbao.uniclub.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderServiceImp {
    boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest);

    List<OrderDto> getAll();

}
