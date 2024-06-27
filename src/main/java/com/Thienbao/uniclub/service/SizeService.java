package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.SizeDto;
import com.Thienbao.uniclub.exception.AlreadyExistException;
import com.Thienbao.uniclub.exception.InsertSizeException;
import com.Thienbao.uniclub.map.SizeMapper;
import com.Thienbao.uniclub.model.Size;
import com.Thienbao.uniclub.payload.request.InsertSizeRequest;
import com.Thienbao.uniclub.repository.SizeRepository;
import com.Thienbao.uniclub.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

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

    @Override
    public boolean insertSize(InsertSizeRequest insertSizeRequest) {
        String newSize = insertSizeRequest.getName().toUpperCase(Locale.ROOT);
        if(!sizeRepository.findByName(newSize).isEmpty()) throw new AlreadyExistException("Size already existed");
        try {
            Size size = new Size();
            size.setName(newSize);
            sizeRepository.save(size);
            return true;
        }catch (Exception ex){
            throw new InsertSizeException("Error insert Size");
        }
    }
}
