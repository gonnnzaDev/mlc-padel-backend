package com.gonnnza.mlc_backend.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.DTO.ProductoAdminGestionDTO;
import com.gonnnza.mlc_backend.DTO.ProductoArticuloDTO;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Repository.CategoriaRepo;
import com.gonnnza.mlc_backend.Repository.ImagenRepo;
import com.gonnnza.mlc_backend.Repository.ProductoRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

// CRUD

public class ProductoService {

    private final ProductoRepo productoRepo;
    private final CategoriaService categoriaService;

    //GETS-----------------------------------------------------------------------------------------------

    public List<Producto> listarProductos() {

        return productoRepo.findAll();
    }

    public List<Producto> listarProductosDeUnaCategoria(String categoria) {
        Categoria categoriaObject = categoriaService.buscarCategoriaPorNombre(categoria);

        return productoRepo.findAllByCategoria(categoriaObject);
    }

    public List<ProductoAdminGestionDTO> listarProductosApartadoAdmin() {
        return productoRepo
                .findAll()
                .stream()
                .map(p -> new ProductoAdminGestionDTO(
                        p.getNombre(), p.getStock(), p.getId()))
                .toList();
    }

    public List<ProductoArticuloDTO> listarProductosEnArticulos() {
        return productoRepo
                .findAll()
                .stream()
                .map(p -> new ProductoArticuloDTO(
                        p.getImagenes().getFirst(),
                        p.getNombre(),
                        p.getPrecioLista(),
                        p.getPrecioFinal(),
                        p.getId()))
                .toList();

    }

    public Producto buscarProductoPorId(Long id) throws NotFoundException {
        return productoRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException("no existe el producto con ID " + id));
    }
    //pOST-----------------------------------------------------------------------------------------------


    public void guardarProducto(AgregarProductoDTO productoDTO) {

        if (productoDTO == null)
            throw new BadRequestException("No existe ese producto");

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

        productoRepo.save(producto);
    }
    public List<Producto> buscarProductos(String nombreProducto) {
        return productoRepo.findByNombreContainingIgnoreCase(nombreProducto);
    }

    //DELETE-----------------------------------------------------------------------------------------------

    public void eliminarProducto(Long id) throws NotFoundException {

        if (!productoRepo.existsById(id))
            throw new NotFoundException("no existe el producto con ID " + id);

        productoRepo.deleteById(id);
    }


    public void eliminarFotoDeProducto(Long idProducto, Long idImagen) {


        Producto producto = buscarProductoPorId(idProducto);

        boolean removido = producto.getImagenes().removeIf(img -> img.getId().equals(idImagen));

        if (!removido)
            throw new NotFoundException("Esa imagen no está asociada a este producto");

        productoRepo.save(producto);

    }

    //PUT-----------------------------------------------------------------------------------------------

    public void actualizarProducto(Long id, ActualizarProductoDTO dto) throws NotFoundException {

        if (dto == null)
            throw new BadRequestException("El Producto no existe");


        Producto productoExistente = productoRepo.findById(id)
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
            for (Imagen nuevaImagen : dto.getImagenes()) {
                nuevaImagen.setProducto(productoExistente);
                productoExistente.getImagenes().add(nuevaImagen);
            }
        }

        productoRepo.save(productoExistente);
    }

    //transaccional para sumar y restar stock despues del momento de transsaccion
    @Transactional
    public void sumarStock(Long productoId, int cantidad) {

        Producto producto = buscarProductoPorId(productoId);

        producto.setStock(producto.getStock() + cantidad);

        productoRepo.save(producto);
    }

    @Transactional
    public void restarStock(Long productoId, int cantidad) {

        Producto producto = buscarProductoPorId(productoId);

        producto.setStock(producto.getStock() - cantidad);

        productoRepo.save(producto);
    }
}
