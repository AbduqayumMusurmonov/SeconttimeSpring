package com.example.seconttimespring.services;

import com.example.seconttimespring.entity.FileStorage;
import com.example.seconttimespring.entity.enummration.FileStorageStatus;
import com.example.seconttimespring.repository.FileStorageRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;
    private final Hashids hashids;
    @Value("${upload.server.folder}")
    private String fileStoragePath;
    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids  = new Hashids(getClass().getName(),6);
    }

    public FileStorage save(MultipartFile multipartFile){
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getName());
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setContentType(multipartFile.getContentType());
        fileStorage.setFileStorageStatus(FileStorageStatus.DRAFT);
        fileStorage.setExtension(getExt(multipartFile.getOriginalFilename()));
        fileStorage = fileStorageRepository.save(fileStorage);

        Date now = new Date();
        String path = String.format("%s/upload_files/%d/%d/%d",this.fileStoragePath,1900 + now.getYear(),1+now.getMonth(),now.getDate());
        File uploadFolder = new File(path);
        if (!uploadFolder.exists() && uploadFolder.mkdirs()){
            System.out.println("Created file");
        }

        fileStorage.setHashIda(hashids.encode(fileStorage.getId()));
        fileStorage.setUploadFolder(path + "/" + fileStorage.getHashIda() + "." + fileStorage.getExtension());
        fileStorageRepository.save(fileStorage);

        uploadFolder = uploadFolder.getAbsoluteFile();
        File file = new File(uploadFolder,String.format("%s,%s",
                fileStorage.getHashIda(),fileStorage.getExtension()));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return fileStorage;
    }

    public FileStorage findByhashId(String hashId){
        return fileStorageRepository.findByHashIda(hashId);
    }

    private String getExt(String fileName){
        String ext = null;
        if (fileName != null && fileName.isEmpty()){
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2){
                ext = fileName.substring(dot + 1);
            }
        }
           return ext;

    }
}
