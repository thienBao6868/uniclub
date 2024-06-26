package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.DetailProductDto;
import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.exception.InsertProductException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.SaveFileException;
import com.Thienbao.uniclub.map.ProductMapper;
import com.Thienbao.uniclub.model.*;
import com.Thienbao.uniclub.model.key.CategoryProductID;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import com.Thienbao.uniclub.model.key.TagProductID;
import com.Thienbao.uniclub.payload.request.InsertProductRequest;
import com.Thienbao.uniclub.repository.*;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private TagProductRepository tagProductRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean insertProduct(InsertProductRequest request) {
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

        try {
            Product product = new Product();
            product.setDesc(request.getDesc());
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setSku(request.getSku());
            // Hàm save sẽ trả về entity Có dữ liệu id của product vừa mới thêm vào
            Product productSave = productRepository.save(product);

            // Xử lý khi save một List file image
            for(MultipartFile file: request.getFiles()){
                // Xử lý save 1 image
                ProductImage productImage = new ProductImage();
                productImage.setName(file.getOriginalFilename());
                productImage.setProduct(productSave);
                Color color = new Color();
                color.setId(request.getIdColor());
                productImage.setColor(color);
                // Save dữ liệu vào bảng product_image
                productImageRepository.save(productImage);
            }


            ProductDetailID productDetailID = new ProductDetailID();
            productDetailID.setIdProduct(productSave.getId());
            productDetailID.setIdSize(request.getIdSize());
            productDetailID.setIdColor(request.getIdColor());

            ProductDetail productDetail = new ProductDetail();
            productDetail.setId(productDetailID);
            productDetail.setQuantity(request.getQuantity());
            productDetail.setPrice(request.getPrice());

            productDetailRepository.save(productDetail);

            CategoryProduct categoryProduct = new CategoryProduct();
            CategoryProductID categoryProductID = new CategoryProductID();
            categoryProductID.setIdProduct(productSave.getId());
            categoryProductID.setIdCategory(request.getIdCategory());
            categoryProduct.setCategoryProductID(categoryProductID);

            categoryProductRepository.save(categoryProduct);

            TagProduct tagProduct = new TagProduct();
            TagProductID tagProductID = new TagProductID();
            tagProductID.setIdProduct(productSave.getId());
            tagProductID.setIdTag(request.getIdTag());
            tagProduct.setTagProductID(tagProductID);

            tagProductRepository.save(tagProduct);


            return true;
        } catch (Exception ex) {
            throw new InsertProductException(ex.getMessage());
        }


    }


    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        Gson gson = new Gson();
        if(redisTemplate.hasKey("products")){
            String dataProductsCached = (Objects.requireNonNull(redisTemplate.opsForValue().get("products"))).toString();
            Type productListType = new TypeToken<ArrayList<ProductDto>>(){}.getType();
            productDtoList = gson.fromJson(dataProductsCached, productListType);
        }else {
            List<Product> productList = productRepository.findAll();

            List<ProductDto> productDTOList = new ArrayList<>();
            productList.forEach(item -> {
                ProductDto productDto = new ProductDto();
                productDto.setId(item.getId());
                productDto.setName(item.getName());
                productDto.setPrice(item.getPrice());
                List<String> images = new ArrayList<>();
                item.getProductImages().forEach(itemImage -> {
                    images.add("http://localhost:8080/file/" + itemImage.getName());
                });
                productDto.setImage(images);
                productDTOList.add(productDto);
            });

            productDtoList.addAll(productDTOList);

            String dataProducts = gson.toJson(productDtoList);
            redisTemplate.opsForValue().set("products", dataProducts);
        }

        return productDtoList;
    }

    @Override
    public DetailProductDto getDetailProduct(int idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Not found product with id :" + idProduct));

        return productMapper.convertToDetailProductDto(product);
    }


}
