package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<TagProduct> tagProductList;
}
