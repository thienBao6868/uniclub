package com.Thienbao.uniclub.model;

import com.Thienbao.uniclub.model.key.TagProductID;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "tag_product")
@Data
public class TagProduct {

    @EmbeddedId
    private TagProductID tagProductID;

    @ManyToOne
    @JoinColumn(name = "id_tag",insertable = false,updatable = false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "id_product",insertable = false,updatable = false)
    private Product product;

}
