package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.SizeDto;
import com.Thienbao.uniclub.payload.request.InsertSizeRequest;

import java.util.List;

public interface SizeServiceImp {
    List<SizeDto> getSizes();
    boolean insertSize(InsertSizeRequest insertSizeRequest);
}
