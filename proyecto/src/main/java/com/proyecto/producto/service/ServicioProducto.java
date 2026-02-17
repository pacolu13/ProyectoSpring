package com.proyecto.producto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.producto.dto.ProductoCreateDTO;
import com.proyecto.producto.dto.ProductoDTO;
import com.proyecto.producto.dto.ProductoUpdateDTO;
import com.proyecto.producto.entity.Producto;
import com.proyecto.producto.mapper.ProductoMapper;
import com.proyecto.producto.repository.RepoProducto;

@Service
public class ServicioProducto {

    private final RepoProducto repoProducto;
    private final ProductoMapper productoMapper;

    public ServicioProducto(RepoProducto repoProducto, ProductoMapper productoMapper) {
        this.repoProducto = repoProducto;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<ProductoDTO> response = productoMapper.toDTOList(repoProducto.findAll());
        return response;
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto response = repoProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(response);
    }

    public ProductoDTO añadirProducto(ProductoCreateDTO prod) {
        Producto producto = productoMapper.toEntity(prod);
        Producto resultado = repoProducto.save(producto);
        return productoMapper.toDTO(resultado);
    }

    public List<ProductoDTO> crearListaProductos(List<Producto> productos) {
        List<ProductoDTO> response = productoMapper.toDTOList(repoProducto.saveAll(productos));
        return response;
    }

    public ProductoDTO actualizarProducto(Long id, ProductoUpdateDTO dto) {
        Producto productoExistente = repoProducto.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado"));

        Producto prodActualizado = productoMapper.updateProductoFromDto(dto, productoExistente);
        return productoMapper.toDTO(repoProducto.save(prodActualizado));
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
