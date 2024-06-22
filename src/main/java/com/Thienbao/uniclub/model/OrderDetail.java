package com.Thienbao.uniclub.model;

import com.Thienbao.uniclub.model.key.OrderDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "order_detail")
@Data
public class OrderDetail {

    @EmbeddedId
    private OrderDetailID orderDetailID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "id_product",insertable = false,updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size",insertable = false,updatable = false)
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_color",insertable = false,updatable = false)
    private Color color;

}
