package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct(InsertProductRequest request);
    List<ProductDto> getAll();
}
