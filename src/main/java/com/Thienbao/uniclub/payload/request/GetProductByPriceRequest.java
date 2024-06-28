package com.Thienbao.uniclub.payload.request;

import lombok.Data;

@Data
public class GetProductByPriceRequest {
    private Integer pageIndex;
    private Integer pageSize;
    private Double lowPrice;
    private Double highPrice;
}
