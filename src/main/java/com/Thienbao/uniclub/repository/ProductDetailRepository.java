package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.ProductDetail;

import com.Thienbao.uniclub.model.key.ProductDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailID> {
    List<ProductDetail> findAllById(ProductDetailID id);
}
