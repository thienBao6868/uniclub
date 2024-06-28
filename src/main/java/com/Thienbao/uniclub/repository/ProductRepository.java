package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.dto.ProductDto;
import com.Thienbao.uniclub.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
