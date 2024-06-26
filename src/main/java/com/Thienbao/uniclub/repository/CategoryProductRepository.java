package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.CategoryProduct;
import com.Thienbao.uniclub.model.key.CategoryProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, CategoryProductID> {
}
