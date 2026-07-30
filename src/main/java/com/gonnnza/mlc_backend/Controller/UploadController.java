package com.gonnnza.mlc_backend.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${upload.dir:uploads}")
    private String direccion;

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path uploadPath = Paths.get(direccion);
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path targetPath = uploadPath.resolve(fileName);
            file.transferTo(targetPath.toFile());

            String fileUrl = "/uploads/" + fileName;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al subir el archivo");
        }
    }
}