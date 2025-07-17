package com.aditya.trucker.controller;

import com.aditya.trucker.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/download/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(new FileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()));
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        List<FileResponse> fileResponses = Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

        return ResponseEntity.ok(fileResponses);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        return ResponseEntity.ok(fileStorageService.getFileResponse(fileName));
    }

    @GetMapping("/files")
    public ResponseEntity<?> listUploadedFiles() {
        return ResponseEntity.ok(fileStorageService.getStoredFiles());
    }

    private static class FileResponse {
        private String fileName;
        private String fileDownloadUri;
        private String fileType;
        private long size;

        public FileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
            this.fileName = fileName;
            this.fileDownloadUri = fileDownloadUri;
            this.fileType = fileType;
            this.size = size;
        }

        // Getters (required for ResponseEntity)
        public String getFileName() { return fileName; }
        public String getFileDownloadUri() { return fileDownloadUri; }
        public String getFileType() { return fileType; }
        public long getSize() { return size; }
    }
}
