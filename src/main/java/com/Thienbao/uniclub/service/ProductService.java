package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Override
    public boolean insertProduct(MultipartFile file) {

        fileServiceImp.saveFile(file);
        return false;
    }
}
