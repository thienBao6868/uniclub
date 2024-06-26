package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.*;
import com.Thienbao.uniclub.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private  CategoryProductMapper categoryProductMapper;
    @Autowired
    private TagMapper tagMapper;

    public DetailProductDto convertToDetailProductDto(Product product){
        DetailProductDto detailProductDto = new DetailProductDto();

        detailProductDto.setId(product.getId());
        detailProductDto.setName(product.getName());
        detailProductDto.setRate(product.getRate());
        detailProductDto.setPrice(product.getPrice());
        detailProductDto.setDescription(product.getDesc());
        detailProductDto.setSku(product.getSku());

        List<ProductDetail> productDetailList = product.getProductDetails();
        List<ProductDetailDto> productDetailDtoList = new ArrayList<>();

        productDetailList.forEach(item ->{
            productDetailDtoList.add(productDetailMapper.convertToProductDetailDto(item));
        });
        detailProductDto.setProductDetailList(productDetailDtoList);

        List<CategoryProduct> categoryProductList = product.getCategoryProductList();
        List<CategoryProductDto> categoryProductDtoList = new ArrayList<>();
        categoryProductList.forEach(item->{
            categoryProductDtoList.add(categoryProductMapper.convertToCategoryProductDto(item));
        });
        detailProductDto.setCategoryList(categoryProductDtoList);

        List<TagProduct> tagProductList = product.getTagProductList();
        List<TagDto> tagProductDtoList = new ArrayList<>();

        tagProductList.forEach(item ->{
            tagProductDtoList.add(tagMapper.convertToTagDto(item.getTag()));
        });
        detailProductDto.setTagList(tagProductDtoList);

        return detailProductDto;
        }
}
