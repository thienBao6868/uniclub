package com.Thienbao.uniclub.repository;


import com.Thienbao.uniclub.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM product p JOIN category_product cp ON cp.categoryProductID.idProduct = p.id AND cp.categoryProductID.idCategory =:categoryId")
    Page<Product> findByCategoryId(@Param("categoryId") int categoryId, Pageable pageable);

    @Query("SELECT p FROM product p JOIN tag_product tp ON tp.tagProductID.idProduct = p.id AND tp.tagProductID.idTag =:tagId")
    Page<Product> findByTagId(@Param("tagId") int tagId, Pageable pageable);

    @Query("SELECT p FROM product p WHERE p.price >:lowPrice AND p.price <:highPrice")
    Page<Product> findByPriceRange(@Param("lowPrice") double lowPrice,@Param("highPrice") double highPrice, Pageable pageable);

    @Query("SELECT p FROM product p WHERE p.name LIKE CONCAT ('%', :name,'%')")
    Page<Product> findByName(@Param("name") String name, Pageable pageable);
}
