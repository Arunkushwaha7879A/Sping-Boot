package com.substring.foodie.substring_foodie.service.impl;

import com.substring.foodie.substring_foodie.controller.RestaurantController;
import com.substring.foodie.substring_foodie.dto.FileData;
import com.substring.foodie.substring_foodie.exception.InvalidFilePathException;
import com.substring.foodie.substring_foodie.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Override
    public FileData uploadFile(MultipartFile file, String path) throws IOException {

        if(path.isBlank()){
            throw new InvalidFilePathException("Invalid upload file!!");
        }

        Path folderpath = Paths.get(path.substring(0, path.lastIndexOf("/") + 1));
        logger.info(folderpath.toString());

        //if folder not exists it will create
        if(!Files.exists(folderpath)){
            Files.createDirectories(folderpath);
        }

        String contentType = file.getContentType();
        if( contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")){

        }
        else{
            throw new InvalidFilePathException("Invalid content !!");
        }

        //file name

        String fileName = path.substring(path.lastIndexOf("/")+1);
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println("FILE NAME = " + fileName);
        System.out.println("EXTENSION = " + fileExtension);
        if(fileExtension.equals("jpg") || fileExtension.equals("jpeg")  || fileExtension.equals("png") ){

        }

        else{
            throw new InvalidFilePathException("Invalid upload extension");
        }





        Path filepath = Paths.get(path);

        Files.copy(file.getInputStream() , filepath , StandardCopyOption.REPLACE_EXISTING);

        FileData fileData = new FileData(fileName, path);

        return fileData;
    }

    @Override
    public void deleteFile(String path) {

    }

    @Override
    public Resource loadFile(String path) {
        return null;
    }
}
