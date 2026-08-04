package com.gonnnza.mlc_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gonnnza.mlc_backend.Enum.RolEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    @JsonIgnore
    private String password;
    @Enumerated(value=EnumType.STRING)
    private RolEnum rol;
    private Boolean activo;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getDireccion() {
        return this.direccion;
    }

    @Generated
    public String getTelefono() {
        return this.telefono;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public RolEnum getRol() {
        return this.rol;
    }

    @Generated
    public Boolean getActivo() {
        return this.activo;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Generated
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    @Generated
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Boolean this$activo = this.getActivo();
        Boolean other$activo = other.getActivo();
        if (this$activo == null ? other$activo != null : !((Object)this$activo).equals(other$activo)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$direccion = this.getDireccion();
        String other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion)) {
            return false;
        }
        String this$telefono = this.getTelefono();
        String other$telefono = other.getTelefono();
        if (this$telefono == null ? other$telefono != null : !this$telefono.equals(other$telefono)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        RolEnum this$rol = this.getRol();
        RolEnum other$rol = other.getRol();
        return !(this$rol == null ? other$rol != null : !((Object)((Object)this$rol)).equals((Object)other$rol));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Usuario;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Boolean $activo = this.getActivo();
        result = result * 59 + ($activo == null ? 43 : ((Object)$activo).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $direccion = this.getDireccion();
        result = result * 59 + ($direccion == null ? 43 : $direccion.hashCode());
        String $telefono = this.getTelefono();
        result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        RolEnum $rol = this.getRol();
        result = result * 59 + ($rol == null ? 43 : ((Object)((Object)$rol)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Usuario(id=" + this.getId() + ", nombre=" + this.getNombre() + ", email=" + this.getEmail() + ", direccion=" + this.getDireccion() + ", telefono=" + this.getTelefono() + ", password=" + this.getPassword() + ", rol=" + String.valueOf((Object)this.getRol()) + ", activo=" + this.getActivo() + ")";
    }

    @Generated
    public Usuario(Long id, String nombre, String email, String direccion, String telefono, String password, RolEnum rol, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    @Generated
    public Usuario() {
    }
}
