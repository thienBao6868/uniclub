package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "size")
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "size")
    private List<Cart> carts;

    @OneToMany(mappedBy = "size")
    private List<OrderDetail> orderDetailList;
}
