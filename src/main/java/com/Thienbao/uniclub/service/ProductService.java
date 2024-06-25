package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.DetailProductDto;
import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.exception.InsertProductException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.SaveFileException;
import com.Thienbao.uniclub.map.ProductMapper;
import com.Thienbao.uniclub.model.Product;
import com.Thienbao.uniclub.model.ProductDetail;
import com.Thienbao.uniclub.model.ProductImage;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import com.Thienbao.uniclub.repository.ProductDetailRepository;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import com.Thienbao.uniclub.repository.ProductImageRepository;
import com.Thienbao.uniclub.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean insertProduct(InsertProductRequest request) {


        try{
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

                ProductDetailID productDetailID = new ProductDetailID();
                productDetailID.setIdProduct(productSave.getId());
                productDetailID.setIdSize(request.getIdSize());
                productDetailID.setIdColor(request.getIdColor());

                ProductDetail productDetail = new ProductDetail();
                productDetail.setId(productDetailID);
                productDetail.setQuantity(request.getQuantity());
                productDetail.setPrice(request.getPrice());

                productDetailRepository.save(productDetail);
            }else {
                throw new SaveFileException();
            }
        }catch (Exception ex){
            throw  new InsertProductException(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        productList.forEach(item->{
            ProductDto productDto = new ProductDto();
            productDto.setName(item.getName());
            productDto.setPrice(item.getPrice());
            List<String> images = new ArrayList<>();
            item.getProductImages().forEach(itemImage ->{
                images.add("http://localhost:8080/file/"+ itemImage.getName());
            });
            productDto.setImage(images);
            productDtoList.add(productDto);
        });
     return productDtoList;
    }

    @Override
    public DetailProductDto getDetailProduct(int idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Not found product with id :" + idProduct));
        return  productMapper.convertToDetailProductDto(product);
    }


}
