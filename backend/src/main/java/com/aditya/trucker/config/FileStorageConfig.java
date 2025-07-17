package com.aditya.trucker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class FileStorageConfig {
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @Value("${file.max-size}")
    private long maxSize;
    
    @Value("${file.base-url}")
    private String fileBaseUrl;
    
    public String getUploadDir() {
        return uploadDir;
    }
    
    public long getMaxSize() {
        return maxSize;
    }
    
    public String getFileBaseUrl() {
        return fileBaseUrl;
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
