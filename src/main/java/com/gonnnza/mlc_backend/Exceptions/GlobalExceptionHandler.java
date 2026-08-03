/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.MethodArgumentNotValidException
 *  org.springframework.web.bind.annotation.ControllerAdvice
 *  org.springframework.web.bind.annotation.ExceptionHandler
 */
package com.gonnnza.mlc_backend.Exceptions;

import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.CredentialsException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={NotFoundException.class})
    public ResponseEntity<?> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status((int)404).body((Object)ex.getMessage());
    }

    @ExceptionHandler(value={CredentialsException.class})
    public ResponseEntity<?> handleCredentialsExceptionHandler(CredentialsException ex) {
        return ResponseEntity.status((int)401).body((Object)ex.getMessage());
    }

    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<?> handleBadRequestExceptionHandler(BadRequestException ex) {
        return ResponseEntity.status((int)400).body((Object)ex.getMessage());
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream().findFirst().map(err -> err.getDefaultMessage()).orElse("Datos inv\u00e1lidos");
        return ResponseEntity.status((int)400).body((Object)mensaje);
    }
}
