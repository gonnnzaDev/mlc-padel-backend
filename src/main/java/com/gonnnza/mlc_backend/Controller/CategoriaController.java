package com.gonnnza.mlc_backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonnnza.mlc_backend.Repository.CategoriaRepo;

import lombok.AllArgsConstructor;

/**
 * CategoriaController
 * 
 * 
 */

@AllArgsConstructor

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaRepo repo;

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok().body(repo.findAll());
    }



}