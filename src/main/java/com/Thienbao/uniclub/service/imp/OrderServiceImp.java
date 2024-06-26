package com.Thienbao.uniclub.service.imp;


import com.Thienbao.uniclub.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderServiceImp {
    boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest);
}
