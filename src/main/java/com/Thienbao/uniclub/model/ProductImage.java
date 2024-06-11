package com.Thienbao.uniclub.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_peoduct")
    private Product product;
}
