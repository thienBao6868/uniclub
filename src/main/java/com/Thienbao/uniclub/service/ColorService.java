package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.ColorDto;
import com.Thienbao.uniclub.exception.AlreadyExistException;
import com.Thienbao.uniclub.exception.InsertColorException;
import com.Thienbao.uniclub.map.ColorMapper;
import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.payload.request.InsertColorRequest;
import com.Thienbao.uniclub.repository.ColorRepository;
import com.Thienbao.uniclub.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ColorService implements ColorServiceImp {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;
    @Override
    public List<ColorDto> getColors() {
       List<Color> colors = colorRepository.findAll();
       return colors.stream().map(colorMapper::convertToColorDto).toList();
    }

    @Override
    public boolean insertColor(InsertColorRequest insertColorRequest) {
        String newColor = insertColorRequest.getName().toUpperCase(Locale.ROOT);
        if(!colorRepository.findByName(newColor).isEmpty()) throw new AlreadyExistException("Color already existed");
        try {
            Color color = new Color();
            color.setName(newColor);
            colorRepository.save(color);
            return true;
        }catch (Exception ex){
            throw new InsertColorException("Error insert color: " + ex.getMessage());
        }
    }
}
