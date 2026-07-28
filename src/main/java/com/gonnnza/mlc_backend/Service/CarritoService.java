package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Carrito;
import com.gonnnza.mlc_backend.Model.ItemCarrito;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Model.Usuario;
import com.gonnnza.mlc_backend.Repository.CarritoRepo;
import com.gonnnza.mlc_backend.Repository.UsuarioRepo;

import com.gonnnza.mlc_backend.Security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarritoService {

    private final CarritoRepo repo;
    private final UsuarioRepo usuarioRepo;
    private final AuthService auth;

    public Carrito buscarCarritoDeUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepo
                .findById(usuarioId)
                .orElseThrow(
                        () -> new NotFoundException("No existe ese usuario"));

        return repo
                .findByUsuario(usuario)
                .orElseThrow(() -> new NotFoundException("Este usuario no tiene carrito"));
    }

    public void agregarProductoAlCarrito(Producto producto, Integer cantidad) {
        Usuario usuario = auth.getUsuarioActivo();
        Carrito carrito = buscarCarritoDeUsuario(usuario.getId());

        // preparo el producto para el carrito
        ItemCarrito aux = new ItemCarrito();
        aux.setProducto(producto);
        aux.setCantidad(cantidad);

        ItemCarrito existente = carrito.getProductos()
                .stream()
                .filter(p -> p.getProducto().getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
        } else {
            carrito.getProductos().add(aux);
        }

        repo.save(carrito);
    }

    public void sacarProductoDeCarrito(Long productoId, Integer cantidad) {

        Usuario usuario = auth.getUsuarioActivo();

        Carrito carrito = buscarCarritoDeUsuario(usuario.getId());

        ItemCarrito existente = carrito.getProductos()
                .stream()
                .filter(p -> p.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No existe ese producto en el carrito"));

        if (existente.getCantidad() - cantidad <= 0) {
            carrito.getProductos().remove(existente);
        } else {
            existente.setCantidad(
                    existente.getCantidad() - cantidad);
        }

        repo.save(carrito);
    }

    public void vaciarCarrito(Long usuarioId) {
        Carrito carrito = buscarCarritoDeUsuario(usuarioId);
        carrito.getProductos().clear();

        repo.save(carrito);
    }

}
