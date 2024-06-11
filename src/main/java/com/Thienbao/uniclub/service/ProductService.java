package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.model.Product;
import com.Thienbao.uniclub.model.ProductImage;
import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import com.Thienbao.uniclub.userRepository.ProductImageRepository;
import com.Thienbao.uniclub.userRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public boolean insertProduct(InsertProductRequest request) {

        boolean isCopySuccess = fileServiceImp.saveFile(request.getFile());
        if(isCopySuccess){
            Product product = new Product();
            product.setDesc(request.getDesc());
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            // Hàm save sẽ trả về entity Có dữ liệu id của product vừa mới thêm vào
            Product productSave =  productRepository.save(product);

            ProductImage productImage = new ProductImage();
            productImage.setName(request.getFile().getOriginalFilename());
            productImage.setProduct(productSave);
            // Save dữ liệu vào bảng product_image
            productImageRepository.save(productImage);

        }
        return false;
    }
}
