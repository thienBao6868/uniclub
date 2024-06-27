package com.Thienbao.uniclub.map;

import com.Thienbao.uniclub.dto.ColorDto;
import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.model.ProductImage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorMapper {
    public ColorDto convertToColorDto(Color color){
        ColorDto colorDto = new ColorDto();
        colorDto.setId(color.getId());
        colorDto.setName(color.getName());
        return colorDto;
    }
    public ColorDto convertToColorDtoOfGetDetailProduct(Color color, int idProduct){
        ColorDto colorDto = new ColorDto();
        colorDto.setId(color.getId());
        colorDto.setName(color.getName());

        List<ProductImage> imageList = color.getProductImageList();
        List<String> images = new ArrayList<>();

        imageList.forEach(item->{
            if(item.getProduct().getId() == idProduct){
                images.add("http://localhost:8080/file/"+item.getName());
            }

        });

        colorDto.setImages(images);
        return colorDto;
    }
}
