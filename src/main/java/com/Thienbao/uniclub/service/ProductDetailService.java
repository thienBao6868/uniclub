package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.exception.AlreadyExistException;
import com.Thienbao.uniclub.exception.InsertProductException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.SaveFileException;
import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.model.Product;
import com.Thienbao.uniclub.model.ProductDetail;
import com.Thienbao.uniclub.model.ProductImage;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import com.Thienbao.uniclub.payload.request.InsertProductDetailRequest;
import com.Thienbao.uniclub.repository.ProductDetailRepository;
import com.Thienbao.uniclub.repository.ProductImageRepository;
import com.Thienbao.uniclub.repository.ProductRepository;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ProductDetailService implements ProductDetailServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Transactional
    @Override
    public boolean insertProductDetail(InsertProductDetailRequest request) {
        try {
            // Save [] file
            MultipartFile[] listImageFile = request.getFiles();
            if (listImageFile.length != 0) {
                for (MultipartFile file : listImageFile) {
                    fileServiceImp.saveFile(file);
                }
            } else {
                throw new SaveFileException("Not found any file");
            }
        } catch (Exception ex) {
            throw new SaveFileException("Error save files");
        }

        try{
            Product product = productRepository.findById(request.getIdProduct()).orElseThrow(()-> new NotFoundException("Not found product with id : " + request.getIdProduct()));
            // Xử lý khi save một List file image
            for(MultipartFile file: request.getFiles()){
                // Xử lý save 1 image
                ProductImage productImage = new ProductImage();
                productImage.setName(file.getOriginalFilename());
                productImage.setProduct(product);
                Color color = new Color();
                color.setId(request.getIdColor());
                productImage.setColor(color);
                // Save dữ liệu vào bảng product_image
                productImageRepository.save(productImage);
            }

            ProductDetailID productDetailID = new ProductDetailID();
            productDetailID.setIdProduct(request.getIdProduct());
            productDetailID.setIdColor(request.getIdColor());
            productDetailID.setIdSize(request.getIdSize());
            Optional<ProductDetail> productDetail = productDetailRepository.findById(productDetailID);
            if (productDetail.isPresent()) throw new AlreadyExistException("Product detail already exits");

            ProductDetail newProductDetail = new ProductDetail();
            newProductDetail.setId(productDetailID);
            newProductDetail.setQuantity(request.getQuantity());
            newProductDetail.setPrice(request.getPricePlus());

            productDetailRepository.save(newProductDetail);

            return true;
        }catch (Exception ex){
            throw new InsertProductException(ex.getMessage());
        }


    }
}
