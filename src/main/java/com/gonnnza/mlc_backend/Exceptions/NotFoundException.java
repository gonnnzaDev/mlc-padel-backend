package com.gonnnza.mlc_backend.Exceptions;

public class NotFoundException
extends RuntimeException {
    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}
