package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndProductIdAndColorIdAndSizeId(int idUser, int idProduct, int idColor, int idSize);
    List<Cart> findByUserId(int idUser);

}
