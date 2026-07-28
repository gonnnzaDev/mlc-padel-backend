package com.gonnnza.mlc_backend.Service;

import java.time.LocalDate;
import java.util.List;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.DTO.ProductoAdminGestionDTO;
import com.gonnnza.mlc_backend.DTO.ProductoArticuloDTO;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Repository.CategoriaRepo;
import com.gonnnza.mlc_backend.Repository.ProductoRepo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class ProductoService {

    private final ProductoRepo repo;
    private final CategoriaRepo categoriaRepo;

    public List<Producto> listarProductos() {
        return repo.findAll();
    }

    public List<Producto> listarProductosDeUnaCategoria(String categoria) {
        Categoria categoriaObject = categoriaRepo.
                findByNombre(categoria).orElseThrow(() ->
                        new NotFoundException("No existe esa categoria")
                        );
        return repo.findAllByCategoria(categoriaObject);
    }

    public List<ProductoAdminGestionDTO> listarProductosApartadoAdmin() {
        return repo
                .findAll()
                .stream()
                .map(p -> new ProductoAdminGestionDTO(
                        p.getNombre(), p.getStock(), p.getId()))
                .toList();

        // hacer una consulta que pase solo la id y el nombre;
    }

    public List<ProductoArticuloDTO> listarProductosEnArticulos() {
        return repo
                .findAll()
                .stream()
                .map(p -> new ProductoArticuloDTO(
                        p.getImagenes().getFirst(),
                        p.getNombre(),
                        p.getPrecioLista(),
                        p.getPrecioFinal(),
                        p.getId()))
                .toList();

        // hacer una consulta que pase solo la id y el nombre;
    }

    public Producto buscarProductoPorId(Long id) throws NotFoundException {
        return repo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No se puede actualizar: no existe el producto con ID " + id));
    }

    public void guardarProducto(AgregarProductoDTO productoDTO) {

        Producto producto = new Producto();
        producto.setCategoria(productoDTO.getCategoria());
        producto.setNombre(productoDTO.getNombre());
        producto.setFechaDeAgregado(LocalDate.now());
        producto.setImagenes(productoDTO.getImagenes());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setImportado(productoDTO.getImportado());
        producto.setPrecioFinal(productoDTO.getPrecioFinal());
        producto.setPrecioLista(productoDTO.getPrecioLista());
        producto.setStock(productoDTO.getStock());

        repo.save(producto);
    }

    public void eliminarProducto(Long id) throws NotFoundException {

        if (!repo.existsById(id))
            throw new NotFoundException("No se puede actualizar: no existe el producto con ID " + id);

        repo.deleteById(id);
    }

    public Producto actualizarProducto(Long id, ActualizarProductoDTO dto) throws NotFoundException {

        Producto productoExistente = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("No se puede actualizar: no existe el producto con ID " + id));

        if (dto.getNombre() != null) {
            productoExistente.setNombre(dto.getNombre());
        }
        if (dto.getDescripcion() != null) {
            productoExistente.setDescripcion(dto.getDescripcion());
        }
        if (dto.getStock() != null) {
            productoExistente.setStock(dto.getStock());
        }
        if (dto.getPrecioLista() != null) {
            productoExistente.setPrecioLista(dto.getPrecioLista());
        }
        if (dto.getPrecioFinal() != null) {
            productoExistente.setPrecioFinal(dto.getPrecioFinal());
        }
        if (dto.getCategoria() != null) {
            productoExistente.setCategoria(dto.getCategoria());
        }
        if (dto.getImportado() != null) {
            productoExistente.setImportado(dto.getImportado());
        }

        if (dto.getImagenes() != null) {
            productoExistente.getImagenes().clear();
            productoExistente.setImagenes(dto.getImagenes());
        }

        return repo.save(productoExistente);
    }

}
