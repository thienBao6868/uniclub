package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    List<Tag> findByName(String name);
}
