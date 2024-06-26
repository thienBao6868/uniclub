package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.SizeDto;
import com.Thienbao.uniclub.map.SizeMapper;
import com.Thienbao.uniclub.model.Size;
import com.Thienbao.uniclub.repository.SizeRepository;
import com.Thienbao.uniclub.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements SizeServiceImp {

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private SizeMapper sizeMapper;
    @Override
    public List<SizeDto> getSizes() {
        List<Size> sizes = sizeRepository.findAll();
        return sizes.stream().map(sizeMapper::convertToSizeDto).toList();
    }
}
