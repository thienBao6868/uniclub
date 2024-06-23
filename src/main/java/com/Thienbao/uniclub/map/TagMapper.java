package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.TagDto;
import com.Thienbao.uniclub.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {

    public TagDto convertToTagDto(Tag tag){
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }
}
