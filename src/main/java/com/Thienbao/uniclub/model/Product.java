package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "rate")
    private int rate;
    @Column(name = "description")
    private String desc;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    private List<TagProduct> tagProductList;

    @OneToMany(mappedBy = "product")
    private List<CategoryProduct> categoryProductList;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;

}
