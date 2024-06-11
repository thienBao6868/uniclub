package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import org.springframework.web.multipart.MultipartFile;

public interface ProductServiceImp {
    boolean insertProduct(InsertProductRequest request);
}
