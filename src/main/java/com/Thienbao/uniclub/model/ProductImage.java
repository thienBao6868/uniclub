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
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;
}
