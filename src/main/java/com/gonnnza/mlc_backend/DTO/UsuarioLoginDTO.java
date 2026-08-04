package com.gonnnza.mlc_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Generated;

public class UsuarioLoginDTO {
    @NotBlank(message="El email es obligatorio")
    private @NotBlank(message="El email es obligatorio") String email;
    @NotBlank(message="El la contrase\u00f1a es obligatoria")
    private @NotBlank(message="El la contrase\u00f1a es obligatoria") String password;

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UsuarioLoginDTO)) {
            return false;
        }
        UsuarioLoginDTO other = (UsuarioLoginDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        return !(this$password == null ? other$password != null : !this$password.equals(other$password));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof UsuarioLoginDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "UsuarioLoginDTO(email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }

    @Generated
    public UsuarioLoginDTO() {
    }

    @Generated
    public UsuarioLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
