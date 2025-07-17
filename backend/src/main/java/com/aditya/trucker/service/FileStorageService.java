package com.aditya.trucker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.aditya.trucker.config.FileStorageConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    
    @Autowired
    private FileStorageConfig config;
    
    public String storeFile(MultipartFile file) throws IOException {
        // Generate a unique filename
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
        
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(config.getUploadDir());
        Files.createDirectories(uploadPath);
        
        // Save the file
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath);
        
        // Return the relative path that can be used in URLs
        return config.getFileBaseUrl() + uniqueFilename;
    }
    
    public byte[] getFile(String filePath) throws IOException {
        Path fullPath = Paths.get(config.getUploadDir()).resolve(filePath.replace("files/", ""));
        return Files.readAllBytes(fullPath);
    }
    
    public void deleteFile(String filePath) throws IOException {
        Path fullPath = Paths.get(config.getUploadDir()).resolve(filePath.replace("files/", ""));
        Files.deleteIfExists(fullPath);
    }

    public List<String> getStoredFiles() {
        try {
            Path uploadPath = Paths.get(config.getUploadDir());
            return Files.list(uploadPath)
                    .map(path -> config.getFileBaseUrl() + path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to list stored files", e);
        }
    }
}
