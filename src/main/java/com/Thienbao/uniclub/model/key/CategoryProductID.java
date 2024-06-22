package com.Thienbao.uniclub.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CategoryProductID implements Serializable {

    @Column(name = "id_category")
    private int idCategory;

    @Column(name = "id_product")
    private int idProduct;
}
