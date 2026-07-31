package com.gonnnza.mlc_backend.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

    private static final int MAX_IMAGENES = 6;

    @Value("${upload.dir:uploads}")
    private String direccion;

    @PreAuthorize("hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("files") List<MultipartFile> files) {
        if (files == null || files.isEmpty())
            return ResponseEntity.badRequest().body("Selecciona al menos un archivo");

        if (files.size() > MAX_IMAGENES)
            return ResponseEntity.badRequest().body("Maximo " + MAX_IMAGENES + " imagenes por carga");

        try {
            Path uploadPath = Paths.get(direccion).toAbsolutePath().normalize();
            if (!Files.exists(uploadPath))
                Files.createDirectories(uploadPath);

            List<String> urls = new ArrayList<>();

            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;

                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path targetPath = uploadPath.resolve(fileName);
                file.transferTo(targetPath.toFile());

                urls.add("/uploads/" + fileName);
            }

            return ResponseEntity.ok(urls);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al subir los archivos");
        }
    }
}