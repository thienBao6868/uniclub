package com.Thienbao.uniclub.model;

import com.Thienbao.uniclub.model.key.CategoryProductID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "category_product")
@Data
public class CategoryProduct {

    @EmbeddedId
    private CategoryProductID categoryProductID;

    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_product",insertable = false,updatable = false)
    private Product product;
}
