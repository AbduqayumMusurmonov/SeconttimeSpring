package com.example.seconttimespring.webrest;

import com.example.seconttimespring.entity.FileStorage;
import com.example.seconttimespring.services.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.Serializable;
import java.net.MalformedURLException;

@RestController
@RequestMapping("api")
public class FileStorageResource implements Serializable {
    private final FileStorageService fileStorageService;
    @Value("${upload.server.folder}")
    private String fileStoragePath;

    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile multipartFile){
        FileStorage fileStorage = fileStorageService.save(multipartFile);
        return ResponseEntity.ok(fileStorage);
    }

    @GetMapping("prewiev/{hashId}")
    public ResponseEntity prewiev(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByhashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName = \""+ UriEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",this.fileStoragePath,fileStorage.getUploadFolder())));

    }


}
