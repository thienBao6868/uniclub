package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.DetailProductDto;
import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.exception.InsertProductException;
import com.Thienbao.uniclub.exception.NotFoundException;
import com.Thienbao.uniclub.exception.SaveFileException;
import com.Thienbao.uniclub.exception.UpdateException;
import com.Thienbao.uniclub.map.ProductMapper;
import com.Thienbao.uniclub.model.*;
import com.Thienbao.uniclub.model.key.CategoryProductID;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import com.Thienbao.uniclub.model.key.TagProductID;
import com.Thienbao.uniclub.payload.request.*;
import com.Thienbao.uniclub.repository.*;
import com.Thienbao.uniclub.service.imp.FileServiceImp;
import com.Thienbao.uniclub.service.imp.ProductServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private TagProductRepository tagProductRepository;

    @Autowired
    private TagRepository tagRepository;

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
            productDetail.setPrice(request.getPricePlus());

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
    public List<ProductDto> getAll(int pageIndex, int pageSize) {
        List<ProductDto> productDtoList = new ArrayList<>();
//        Gson gson = new Gson();
//        if(redisTemplate.hasKey("products")){
//            String dataProductsCached = (Objects.requireNonNull(redisTemplate.opsForValue().get("products"))).toString();
//            Type productListType = new TypeToken<ArrayList<ProductDto>>(){}.getType();
//            productDtoList = gson.fromJson(dataProductsCached, productListType);
//        }else {
            Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
            Page<Product> productList = productRepository.findAll(pageable);

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

//            String dataProducts = gson.toJson(productDtoList);
//            redisTemplate.opsForValue().set("products", dataProducts);
//        }

        return productDtoList;
    }

    @Override
    public DetailProductDto getDetailProduct(int idProduct) {
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new NotFoundException("Not found product with id :" + idProduct));

        return productMapper.convertToDetailProductDto(product);
    }

    @Override
    public List<ProductDto> getProductsByCategory(GetProductByCategoryRequest request) {

       int pageIndex = (request.getPageIndex() != null) ? request.getPageIndex() : 1;
       int pageSize = (request.getPageSize() != null) ? request.getPageSize() : 9;
       categoryRepository.findById(request.getIdCategory()).orElseThrow(()-> new NotFoundException("Not found category with id" + request.getIdCategory()));

        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Product> products = productRepository.findByCategoryId(request.getIdCategory(), pageable);

        List<ProductDto> productDTOList = new ArrayList<>();
        products.forEach(item -> {
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

        return productDTOList;
    }

    @Override
    public List<ProductDto> getProductsByTag(GetProductByTagRequest request) {
        tagRepository.findById(request.getIdTag()).orElseThrow(()-> new NotFoundException("Not found tag with id-Tag"));
        int pageIndex = (request.getPageIndex() != null) ? request.getPageIndex() : 1;
        int pageSize = (request.getPageSize() != null) ? request.getPageSize() : 9;
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Product> products = productRepository.findByTagId(request.getIdTag(), pageable);

        List<ProductDto> productDTOList = new ArrayList<>();
        products.forEach(item -> {
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

        return productDTOList;
    }

    @Override
    public List<ProductDto> getProductByPrice(GetProductByPriceRequest request) {

        int pageIndex = (request.getPageIndex() != null) ? request.getPageIndex() : 1;
        int pageSize = (request.getPageSize() != null) ? request.getPageSize() : 9;
        double lowPrice = (request.getLowPrice() != null)? request.getLowPrice() : 0;
        double highPrice = (request.getHighPrice() != null) ? request.getHighPrice() : 2000;

        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Product> products = productRepository.findByPriceRange(lowPrice,highPrice,pageable);

        List<ProductDto> productDTOList = new ArrayList<>();
        products.forEach(item -> {
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
        return productDTOList;
    }

    @Override
    public List<ProductDto> getProductByName(GetProductByNameRequest request) {
        int pageIndex = (request.getPageIndex() != null) ? request.getPageIndex() : 1;
        int pageSize = (request.getPageSize() != null) ? request.getPageSize() : 9;
        Pageable pageable = PageRequest.of(pageIndex-1,pageSize);
        Page<Product> products = productRepository.findByName(request.getName(), pageable);

        List<ProductDto> productDTOList = new ArrayList<>();
        products.forEach(item -> {
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
        return productDTOList;
    }

    @Override
    public boolean updateProduct(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.getIdProduct()).orElseThrow(()-> new NotFoundException("Not found product with id: "+ updateProductRequest.getIdProduct()));
        try {
            product.setName(updateProductRequest.getName());
            product.setPrice(updateProductRequest.getPrice());
            product.setRate(updateProductRequest.getRate());
            product.setDesc(updateProductRequest.getDesc());
            product.setSku(updateProductRequest.getSku());
            productRepository.save(product);
            return true;
        }catch (Exception ex){
            throw new UpdateException("Error update Product");
        }

    }


}
