package com.Thienbao.uniclub.service;

import com.Thienbao.uniclub.service.imp.FileServiceImp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService implements FileServiceImp {

    @Value("${path.save-file}")
    private String pathSaveFile;

    @Override
    public boolean saveFile(MultipartFile file) {
        boolean isSuccess = false;
        try{
            Path root = Paths.get(pathSaveFile);
            if (!Files.exists(root)){
                Files.createDirectory(root);
            }
            // User/thienbao/Document/upload/namefile
            Files.copy(file.getInputStream(),root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            isSuccess = true;
        }catch (Exception ex){
            System.out.println("Error save file : " + ex.getMessage());
        }
        return isSuccess;
    }
}
