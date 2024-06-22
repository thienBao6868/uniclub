package com.Thienbao.uniclub.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailID implements Serializable {
    @Column(name = "id_order")
    private int idOrder;
    @Column(name = "id_product")
    private int idProduct;
    @Column(name = "id_size")
    private int idSize;
    @Column(name = "id_color")
    private int idColor;
}
