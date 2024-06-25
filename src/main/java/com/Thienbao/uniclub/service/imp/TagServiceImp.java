package com.Thienbao.uniclub.service.imp;

import com.Thienbao.uniclub.dto.TagDto;
import com.Thienbao.uniclub.payload.request.InsertTagRequest;

import java.util.List;

public interface TagServiceImp {
    List<TagDto> getTags();
    boolean insertTag(InsertTagRequest insertTagRequest);
}
