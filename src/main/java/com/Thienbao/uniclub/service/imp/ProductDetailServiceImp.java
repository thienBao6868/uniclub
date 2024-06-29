package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.payload.request.InsertProductDetailRequest;
import com.Thienbao.uniclub.payload.request.UpdateProductDetailRequest;

public interface ProductDetailServiceImp {
    boolean insertProductDetail(InsertProductDetailRequest request);

    boolean updateProductDetail(UpdateProductDetailRequest request);
}
