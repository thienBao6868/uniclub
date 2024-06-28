package com.Thienbao.uniclub.repository;


import com.Thienbao.uniclub.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM product p JOIN category_product cp ON cp.categoryProductID.idProduct = p.id AND cp.categoryProductID.idCategory =:categoryId")
    Page<Product> findByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);
}
