package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.DetailProductDto;
import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.payload.request.GetProductByCategoryRequest;
import com.Thienbao.uniclub.payload.request.GetProductByTagRequest;
import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct(InsertProductRequest request);
    List<ProductDto> getAll(int pageIndex, int pageSize);
    DetailProductDto getDetailProduct(int idProduct);
    List<ProductDto> getProductsByCategory(GetProductByCategoryRequest request);

    List<ProductDto> getProductsByTag(GetProductByTagRequest request);
}
