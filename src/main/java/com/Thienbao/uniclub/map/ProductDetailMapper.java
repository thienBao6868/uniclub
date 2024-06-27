package com.Thienbao.uniclub.map;


import com.Thienbao.uniclub.dto.ProductDetailDto;
import com.Thienbao.uniclub.model.ProductDetail;
import com.Thienbao.uniclub.model.ProductImage;
import com.Thienbao.uniclub.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailMapper {
    @Autowired
    private ColorMapper colorMapper;
    @Autowired
    private SizeMapper sizeMapper;
    @Autowired
    private ProductImageRepository productImageRepository;

    public ProductDetailDto convertToProductDetailDto(ProductDetail productDetail, int idProduct){
        ProductDetailDto productDetailDto = new ProductDetailDto();

            productDetailDto.setColor(colorMapper.convertToColorDtoOfGetDetailProduct(productDetail.getColor(),idProduct));
            productDetailDto.setSize(sizeMapper.convertToSizeDto(productDetail.getSize()));
            productDetailDto.setQuantity(productDetail.getQuantity());
            productDetailDto.setPricePlus(productDetail.getPrice());

        return  productDetailDto;
    }
}
