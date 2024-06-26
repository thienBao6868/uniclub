package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.SizeDto;
import com.Thienbao.uniclub.model.Size;
import org.springframework.stereotype.Service;

@Service
public class SizeMapper {

    public SizeDto convertToSizeDto(Size size){
        SizeDto sizeDto = new SizeDto();
        sizeDto.setId(size.getId());
        sizeDto.setName(size.getName());
        return sizeDto;
    }
}
