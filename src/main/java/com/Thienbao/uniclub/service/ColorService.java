package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.ColorDto;
import com.Thienbao.uniclub.map.ColorMapper;
import com.Thienbao.uniclub.model.Color;
import com.Thienbao.uniclub.repository.ColorRepository;
import com.Thienbao.uniclub.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
