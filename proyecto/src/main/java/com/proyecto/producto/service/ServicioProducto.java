package com.proyecto.producto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.producto.entity.Producto;
import com.proyecto.producto.repository.RepoProducto;

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

    public List<Producto> crearListaProductos(List<Producto> productos) {
        return repoProducto.saveAll(productos);
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

    public void eliminarListaProductos(List<Long> ids) {
        for (Long id : ids) {
            if (!repoProducto.existsById(id)) {
                throw new RuntimeException("Producto con ID " + id + " no encontrado");
            }
        }
        repoProducto.deleteAllById(ids);
    }
}
