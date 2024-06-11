package com.Thienbao.uniclub.userRepository;

import com.Thienbao.uniclub.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
