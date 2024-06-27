package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.*;
import com.Thienbao.uniclub.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailMapper {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SizeMapper sizeMapper;

    @Autowired
    private ColorMapper colorMapper;

    public OrderDetailDto convertToOrderDetailDto(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();

        OrderDto orderDto = orderMapper.convertToOrderDto(orderDetail.getOrder());
        orderDetailDto.setOrder(orderDto);

        ProductDto productDto = productMapper.convertToProductDto(orderDetail.getProduct());
        orderDetailDto.setProduct(productDto);

        SizeDto sizeDto = sizeMapper.convertToSizeDto(orderDetail.getSize());
        orderDetailDto.setSizeDto(sizeDto);

        ColorDto colorDto = colorMapper.convertToColorDto(orderDetail.getColor());
        orderDetailDto.setColorDto(colorDto);

        orderDetailDto.setQuantity(orderDetail.getQuantity());
        orderDetailDto.setPrice(orderDetail.getPrice());

        return  orderDetailDto;
    }
}
