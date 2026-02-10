package com.proyecto.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.Entities.Producto;
import com.proyecto.Repositories.RepoProducto;

@Service
public class ServicioProducto {

    private final RepoProducto repoProducto;

    public ServicioProducto(RepoProducto repoProducto) {
        this.repoProducto = repoProducto;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return repoProducto.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return repoProducto.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto crearProducto(Producto producto) {
        return repoProducto.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto productoExistente = repoProducto.findById(id).orElseThrow(
        () -> new RuntimeException("Producto no encontrado"));
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        return repoProducto.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        if (!repoProducto.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repoProducto.deleteById(id);
    }
}
