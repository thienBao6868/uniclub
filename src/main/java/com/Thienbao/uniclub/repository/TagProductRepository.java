package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.TagProduct;
import com.Thienbao.uniclub.model.key.TagProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagProductRepository extends JpaRepository<TagProduct, TagProductID> {
}
