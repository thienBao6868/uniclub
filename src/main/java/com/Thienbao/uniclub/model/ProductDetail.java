package com.Thienbao.uniclub.model;

import com.Thienbao.uniclub.model.key.ProductDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product_detail")
@Data
public class ProductDetail {

    @EmbeddedId
    private ProductDetailID id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

}
