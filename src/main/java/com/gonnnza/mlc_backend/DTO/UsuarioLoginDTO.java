package com.gonnnza.mlc_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioLoginDTO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioLoginDTO {

    private String email;
    private String password;

    
}