package com.Thienbao.uniclub.map;


import com.Thienbao.uniclub.dto.ProductDetailDto;
import com.Thienbao.uniclub.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailMapper {
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private SizeMapper sizeMapper;
    public ProductDetailDto convertToProductDetailDto(ProductDetail productDetail){
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setColor(colorMapper.convertToColorDto(productDetail.getColor()));
        productDetailDto.setSize(sizeMapper.convertToSizeDto(productDetail.getSize()));
        productDetailDto.setQuantity(productDetail.getQuantity());
        productDetailDto.setPricePlus(productDetail.getPrice());
        return  productDetailDto;
    }
}
