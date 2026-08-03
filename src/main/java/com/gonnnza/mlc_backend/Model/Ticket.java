/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.CascadeType
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.JoinColumn
 *  jakarta.persistence.ManyToOne
 *  jakarta.persistence.OneToMany
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.gonnnza.mlc_backend.Model;

import com.gonnnza.mlc_backend.Enum.EstadoEnum;
import com.gonnnza.mlc_backend.Model.Pedido;
import com.gonnnza.mlc_backend.Model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="referencia_de_pago", unique=true)
    private String referenciaDePago;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String provincia;
    @Column(name="codigo_postal")
    private String codigoPostal;
    private String nota;
    @Column(name="metodo_pago")
    private String metodoPago;
    @Enumerated(value=EnumType.STRING)
    private EstadoEnum estado;
    @Column(name="fecha_realizado")
    private LocalDateTime fechaRealizado;
    @Column(name="precio_total")
    private BigDecimal preciototal;
    @OneToMany(mappedBy="ticket", cascade={CascadeType.ALL}, orphanRemoval=true)
    private List<Pedido> pedidos;

    @Generated
    public Ticket(Long id, String referenciaDePago, Usuario usuario, String nombre, String email, String telefono, String direccion, String ciudad, String provincia, String codigoPostal, String nota, String metodoPago, EstadoEnum estado, LocalDateTime fechaRealizado, BigDecimal preciototal, List<Pedido> pedidos) {
        this.id = id;
        this.referenciaDePago = referenciaDePago;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.nota = nota;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.fechaRealizado = fechaRealizado;
        this.preciototal = preciototal;
        this.pedidos = pedidos;
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getReferenciaDePago() {
        return this.referenciaDePago;
    }

    @Generated
    public Usuario getUsuario() {
        return this.usuario;
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
    public String getTelefono() {
        return this.telefono;
    }

    @Generated
    public String getDireccion() {
        return this.direccion;
    }

    @Generated
    public String getCiudad() {
        return this.ciudad;
    }

    @Generated
    public String getProvincia() {
        return this.provincia;
    }

    @Generated
    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    @Generated
    public String getNota() {
        return this.nota;
    }

    @Generated
    public String getMetodoPago() {
        return this.metodoPago;
    }

    @Generated
    public EstadoEnum getEstado() {
        return this.estado;
    }

    @Generated
    public LocalDateTime getFechaRealizado() {
        return this.fechaRealizado;
    }

    @Generated
    public BigDecimal getPreciototal() {
        return this.preciototal;
    }

    @Generated
    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setReferenciaDePago(String referenciaDePago) {
        this.referenciaDePago = referenciaDePago;
    }

    @Generated
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Generated
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Generated
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Generated
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Generated
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Generated
    public void setNota(String nota) {
        this.nota = nota;
    }

    @Generated
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Generated
    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    @Generated
    public void setFechaRealizado(LocalDateTime fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    @Generated
    public void setPreciototal(BigDecimal preciototal) {
        this.preciototal = preciototal;
    }

    @Generated
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$referenciaDePago = this.getReferenciaDePago();
        String other$referenciaDePago = other.getReferenciaDePago();
        if (this$referenciaDePago == null ? other$referenciaDePago != null : !this$referenciaDePago.equals(other$referenciaDePago)) {
            return false;
        }
        Usuario this$usuario = this.getUsuario();
        Usuario other$usuario = other.getUsuario();
        if (this$usuario == null ? other$usuario != null : !((Object)this$usuario).equals(other$usuario)) {
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
        String this$telefono = this.getTelefono();
        String other$telefono = other.getTelefono();
        if (this$telefono == null ? other$telefono != null : !this$telefono.equals(other$telefono)) {
            return false;
        }
        String this$direccion = this.getDireccion();
        String other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion)) {
            return false;
        }
        String this$ciudad = this.getCiudad();
        String other$ciudad = other.getCiudad();
        if (this$ciudad == null ? other$ciudad != null : !this$ciudad.equals(other$ciudad)) {
            return false;
        }
        String this$provincia = this.getProvincia();
        String other$provincia = other.getProvincia();
        if (this$provincia == null ? other$provincia != null : !this$provincia.equals(other$provincia)) {
            return false;
        }
        String this$codigoPostal = this.getCodigoPostal();
        String other$codigoPostal = other.getCodigoPostal();
        if (this$codigoPostal == null ? other$codigoPostal != null : !this$codigoPostal.equals(other$codigoPostal)) {
            return false;
        }
        String this$nota = this.getNota();
        String other$nota = other.getNota();
        if (this$nota == null ? other$nota != null : !this$nota.equals(other$nota)) {
            return false;
        }
        String this$metodoPago = this.getMetodoPago();
        String other$metodoPago = other.getMetodoPago();
        if (this$metodoPago == null ? other$metodoPago != null : !this$metodoPago.equals(other$metodoPago)) {
            return false;
        }
        EstadoEnum this$estado = this.getEstado();
        EstadoEnum other$estado = other.getEstado();
        if (this$estado == null ? other$estado != null : !((Object)((Object)this$estado)).equals((Object)other$estado)) {
            return false;
        }
        LocalDateTime this$fechaRealizado = this.getFechaRealizado();
        LocalDateTime other$fechaRealizado = other.getFechaRealizado();
        if (this$fechaRealizado == null ? other$fechaRealizado != null : !((Object)this$fechaRealizado).equals(other$fechaRealizado)) {
            return false;
        }
        BigDecimal this$preciototal = this.getPreciototal();
        BigDecimal other$preciototal = other.getPreciototal();
        if (this$preciototal == null ? other$preciototal != null : !((Object)this$preciototal).equals(other$preciototal)) {
            return false;
        }
        List<Pedido> this$pedidos = this.getPedidos();
        List<Pedido> other$pedidos = other.getPedidos();
        return !(this$pedidos == null ? other$pedidos != null : !((Object)this$pedidos).equals(other$pedidos));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Ticket;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $referenciaDePago = this.getReferenciaDePago();
        result = result * 59 + ($referenciaDePago == null ? 43 : $referenciaDePago.hashCode());
        Usuario $usuario = this.getUsuario();
        result = result * 59 + ($usuario == null ? 43 : ((Object)$usuario).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $telefono = this.getTelefono();
        result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
        String $direccion = this.getDireccion();
        result = result * 59 + ($direccion == null ? 43 : $direccion.hashCode());
        String $ciudad = this.getCiudad();
        result = result * 59 + ($ciudad == null ? 43 : $ciudad.hashCode());
        String $provincia = this.getProvincia();
        result = result * 59 + ($provincia == null ? 43 : $provincia.hashCode());
        String $codigoPostal = this.getCodigoPostal();
        result = result * 59 + ($codigoPostal == null ? 43 : $codigoPostal.hashCode());
        String $nota = this.getNota();
        result = result * 59 + ($nota == null ? 43 : $nota.hashCode());
        String $metodoPago = this.getMetodoPago();
        result = result * 59 + ($metodoPago == null ? 43 : $metodoPago.hashCode());
        EstadoEnum $estado = this.getEstado();
        result = result * 59 + ($estado == null ? 43 : ((Object)((Object)$estado)).hashCode());
        LocalDateTime $fechaRealizado = this.getFechaRealizado();
        result = result * 59 + ($fechaRealizado == null ? 43 : ((Object)$fechaRealizado).hashCode());
        BigDecimal $preciototal = this.getPreciototal();
        result = result * 59 + ($preciototal == null ? 43 : ((Object)$preciototal).hashCode());
        List<Pedido> $pedidos = this.getPedidos();
        result = result * 59 + ($pedidos == null ? 43 : ((Object)$pedidos).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Ticket(id=" + this.getId() + ", referenciaDePago=" + this.getReferenciaDePago() + ", usuario=" + String.valueOf(this.getUsuario()) + ", nombre=" + this.getNombre() + ", email=" + this.getEmail() + ", telefono=" + this.getTelefono() + ", direccion=" + this.getDireccion() + ", ciudad=" + this.getCiudad() + ", provincia=" + this.getProvincia() + ", codigoPostal=" + this.getCodigoPostal() + ", nota=" + this.getNota() + ", metodoPago=" + this.getMetodoPago() + ", estado=" + String.valueOf((Object)this.getEstado()) + ", fechaRealizado=" + String.valueOf(this.getFechaRealizado()) + ", preciototal=" + String.valueOf(this.getPreciototal()) + ", pedidos=" + String.valueOf(this.getPedidos()) + ")";
    }

    @Generated
    public Ticket() {
    }
}
