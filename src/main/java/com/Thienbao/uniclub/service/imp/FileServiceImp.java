package com.Thienbao.uniclub.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    boolean saveFile(MultipartFile file);
}
