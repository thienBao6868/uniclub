package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.ProductDetail;
import com.Thienbao.uniclub.model.key.ProductDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, ProductDetailID> {
}
