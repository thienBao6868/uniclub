package com.Thienbao.uniclub.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface ProductServiceImp {
    boolean insertProduct(MultipartFile file);
}
