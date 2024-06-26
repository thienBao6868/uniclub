package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.model.Product;
import com.Thienbao.uniclub.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    ProductImage findByProductAndColor(Product product, Color color);
}
