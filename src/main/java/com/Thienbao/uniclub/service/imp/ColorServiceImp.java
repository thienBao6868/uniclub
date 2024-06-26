package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.ColorDto;
import com.Thienbao.uniclub.payload.request.InsertColorRequest;

import java.util.List;

public interface ColorServiceImp {
    List<ColorDto> getColors();
    boolean insertColor(InsertColorRequest insertColorRequest);
}
