/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.http.ResponseEntity
 *  org.springframework.security.access.prepost.PreAuthorize
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 *  org.springframework.web.multipart.MultipartFile
 */
package com.gonnnza.mlc_backend.Controller;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
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
@RequestMapping(value={"/upload"})
public class UploadController {
    private static final int MAX_IMAGENES = 6;
    @Value(value="${upload.dir:uploads}")
    private String direccion;

    @PreAuthorize(value="hasAnyRole('ADMIN', 'DUENIO')")
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam(value="files") List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body((Object)"Selecciona al menos un archivo");
        }
        if (files.size() > 6) {
            return ResponseEntity.badRequest().body((Object)"Maximo 6 imagenes por carga");
        }
        try {
            Path uploadPath = Paths.get(this.direccion, new String[0]).toAbsolutePath().normalize();
            if (!Files.exists(uploadPath, new LinkOption[0])) {
                Files.createDirectories(uploadPath, new FileAttribute[0]);
            }
            ArrayList<CallSite> urls = new ArrayList<CallSite>();
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path targetPath = uploadPath.resolve(fileName);
                file.transferTo(targetPath.toFile());
                urls.add((CallSite)((Object)("/uploads/" + fileName)));
            }
            return ResponseEntity.ok(urls);
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body((Object)"Error al subir los archivos");
        }
    }
}
