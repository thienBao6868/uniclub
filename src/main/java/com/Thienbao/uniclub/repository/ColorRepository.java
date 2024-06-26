package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findByName(String name);

}
