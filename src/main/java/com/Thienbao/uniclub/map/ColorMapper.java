package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.ColorDto;
import com.Thienbao.uniclub.model.Color;
import org.springframework.stereotype.Service;

@Service
public class ColorMapper {
    public ColorDto convertToColorDto(Color color){
        ColorDto colorDto = new ColorDto();
        colorDto.setId(color.getId());
        colorDto.setName(color.getName());
        return colorDto;
    }
}
