package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.TagProductDto;
import com.Thienbao.uniclub.model.TagProduct;
import org.springframework.stereotype.Service;

@Service
public class TagProductMapper {

    public TagProductDto convertToTagProductDto(TagProduct tagProduct){
        TagProductDto tagProductDto = new TagProductDto() ;
        tagProductDto.setId(tagProduct.getTag().getId());
        tagProductDto.setName(tagProduct.getTag().getName());
        return tagProductDto;
    }
}
