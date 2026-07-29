package com.gonnnza.mlc_backend.DTO;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "El la contraseña es obligatoria")
    private String password;


}