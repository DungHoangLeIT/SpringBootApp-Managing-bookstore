package com.example.employee.controller;

import com.example.employee.Util.ResponseWrapper;
import com.example.employee.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
     FileService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file, @RequestParam String fileType) throws IOException {
        return ResponseEntity.ok(new ResponseWrapper(fileService.uploadFile(file, fileType)));
    }
}
