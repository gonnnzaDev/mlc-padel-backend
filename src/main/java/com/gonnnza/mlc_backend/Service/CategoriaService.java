package com.gonnnza.mlc_backend.Service;

import com.gonnnza.mlc_backend.Exceptions.BadRequestException;
import com.gonnnza.mlc_backend.Exceptions.NotFoundException;
import com.gonnnza.mlc_backend.Model.Categoria;
import com.gonnnza.mlc_backend.Repository.CategoriaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepo repo;

    public Categoria buscarCategoriaPorNombre(String nombre) {
        return repo.findByNombre(nombre).orElseThrow(() ->
                new NotFoundException("No existe esa categoria"));
    }

    public List<Categoria> listarCategorias() {
        return repo.findAll();
    }

    public void agregarCategoria(String nombre) {
        if (nombre == null)
            throw new BadRequestException("Categoria invalida");

        Categoria categoria = new Categoria(nombre);

        repo.save(categoria);
    }

    public void eliminarCategoria(Integer id) {
        repo.deleteById(id);
    }

}
