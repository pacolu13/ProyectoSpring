package com.proyecto.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.product.dto.ProductCreateDTO;
import com.proyecto.product.dto.ProductDTO;
import com.proyecto.product.dto.ProductUpdateDTO;
import com.proyecto.product.entity.Product;
import com.proyecto.product.mapper.ProductMapper;
import com.proyecto.product.repository.ProductRepository;

@Service
@SuppressWarnings("null")
public class ProductService {

    private final ProductRepository repoProducto;
    private final ProductMapper productoMapper;

    public ProductService(ProductRepository repoProducto, ProductMapper productoMapper) {
        this.repoProducto = repoProducto;
        this.productoMapper = productoMapper;
    }

    public List<ProductDTO> obtenerTodosLosProductos() {
        List<ProductDTO> response = productoMapper.toDTOList(repoProducto.findAll());
        return response;
    }

    public ProductDTO obtenerProductoPorId(Long id) {
        Product response = repoProducto.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(response);
    }

    public List<ProductDTO> añadirListaProducto(List<ProductCreateDTO> productos) {
        List<Product> listaProductos = productoMapper.toEntityList(productos);
        List<ProductDTO> response = productoMapper.toDTOList(repoProducto.saveAll(listaProductos));
        return response;
    }

    public ProductDTO actualizarProducto(Long id, ProductUpdateDTO dto) {
        Product productoExistente = repoProducto.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado"));

        Product prodActualizado = productoMapper.updateProductoFromDto(dto, productoExistente);
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
