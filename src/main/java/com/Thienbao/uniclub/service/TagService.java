package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.dto.TagDto;
import com.Thienbao.uniclub.exception.AlreadyExistException;
import com.Thienbao.uniclub.exception.InsertTagException;
import com.Thienbao.uniclub.map.TagMapper;
import com.Thienbao.uniclub.model.Tag;
import com.Thienbao.uniclub.payload.request.InsertTagRequest;
import com.Thienbao.uniclub.repository.TagRepository;
import com.Thienbao.uniclub.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TagService implements TagServiceImp {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tagMapper::convertToTagDto).toList();
    }

    @Override
    public boolean insertTag(InsertTagRequest insertTagRequest) {
        List<Tag> tags = tagRepository.findByName(insertTagRequest.getName());
        if(!tags.isEmpty()) throw new AlreadyExistException("Tag Name Already Exist");
        try{
            Tag tag = new Tag();
            tag.setName(insertTagRequest.getName());
            tagRepository.save(tag);
            return true;
        }catch (Exception e){
            throw new InsertTagException("Error insert tag: " + e.getMessage());
        }
    }
}
