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
    @JoinColumn(name = "id_product", insertable = false,updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size", insertable = false, updatable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_color",insertable = false, updatable = false)
    private Color color;

}
