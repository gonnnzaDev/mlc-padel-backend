/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.transaction.Transactional
 *  lombok.Generated
 *  org.springframework.stereotype.Service
 */
package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.DTO.ActualizarProductoDTO;
import com.gonnnza.mlc_backend.DTO.AgregarProductoDTO;
import com.gonnnza.mlc_backend.DTO.ProductoAdminGestionDTO;
import com.gonnnza.mlc_backend.DTO.ProductoArticuloDTO;
import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Model.Imagen;
import com.gonnnza.mlc_backend.Model.Producto;
import com.gonnnza.mlc_backend.Repository.ProductoRepo;
import com.gonnnza.mlc_backend.Service.CategoriaService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepo productoRepo;
    private final CategoriaService categoriaService;

    public List<Producto> listarProductos() {
        return this.productoRepo.findAll();
    }

    public List<Producto> listarProductosDeUnaCategoria(String categoria) {
        Categoria categoriaObject = this.categoriaService.buscarCategoriaPorNombre(categoria);
        return this.productoRepo.findAllByCategoria(categoriaObject);
    }

    public List<ProductoAdminGestionDTO> listarProductosApartadoAdmin() {
        return this.productoRepo.findAll().stream().map(p -> new ProductoAdminGestionDTO(p.getNombre(), p.getStock(), p.getId())).toList();
    }

    public List<ProductoArticuloDTO> listarProductosEnArticulos() {
        return this.productoRepo.findAll().stream().map(p -> new ProductoArticuloDTO(p.getImagenes() != null && !p.getImagenes().isEmpty() ? p.getImagenes().getFirst() : null, p.getNombre(), p.getPrecioLista(), p.getPrecioFinal(), p.getId(), p.getStock())).toList();
    }

    public Producto buscarProductoPorId(Long id) throws NotFoundException {
        return (Producto)this.productoRepo.findById(id).orElseThrow(() -> new NotFoundException("no existe el producto con ID " + id));
    }

    public void guardarProducto(AgregarProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new BadRequestException("No existe ese producto");
        }
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
        this.productoRepo.save(producto);
    }

    public List<Producto> buscarProductos(String nombreProducto) {
        return this.productoRepo.findByNombreContainingIgnoreCase(nombreProducto);
    }

    public void eliminarProducto(Long id) throws NotFoundException {
        if (!this.productoRepo.existsById(id)) {
            throw new NotFoundException("no existe el producto con ID " + id);
        }
        this.productoRepo.deleteById(id);
    }

    public void eliminarFotoDeProducto(Long idProducto, Long idImagen) {
        Producto producto = this.buscarProductoPorId(idProducto);
        boolean removido = producto.getImagenes().removeIf(img -> img.getId().equals(idImagen));
        if (!removido) {
            throw new NotFoundException("Esa imagen no est\u00e1 asociada a este producto");
        }
        this.productoRepo.save(producto);
    }

    public void actualizarProducto(Long id, ActualizarProductoDTO dto) throws NotFoundException {
        if (dto == null) {
            throw new BadRequestException("El Producto no existe");
        }
        Producto productoExistente = (Producto)this.productoRepo.findById(id).orElseThrow(() -> new NotFoundException("No se puede actualizar: no existe el producto con ID " + id));
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
        this.productoRepo.save(productoExistente);
    }

    @Transactional
    public void sumarStock(Long productoId, int cantidad) {
        Producto producto = this.buscarProductoPorId(productoId);
        producto.setStock(producto.getStock() + cantidad);
        this.productoRepo.save(producto);
    }

    @Transactional
    public void restarStock(Long productoId, int cantidad) {
        if (cantidad <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a 0");
        }
        Producto producto = this.buscarProductoPorId(productoId);
        if (producto.getStock() < cantidad) {
            throw new BadRequestException("Stock insuficiente para el producto " + producto.getNombre() + " (disponible: " + producto.getStock() + ")");
        }
        producto.setStock(producto.getStock() - cantidad);
        this.productoRepo.save(producto);
    }

    @Generated
    public ProductoService(ProductoRepo productoRepo, CategoriaService categoriaService) {
        this.productoRepo = productoRepo;
        this.categoriaService = categoriaService;
    }
}
