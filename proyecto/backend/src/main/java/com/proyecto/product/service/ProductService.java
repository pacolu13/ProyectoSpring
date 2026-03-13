package com.proyecto.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.product.dto.ProductCreateDTO;
import com.proyecto.product.dto.ProductDTO;
import com.proyecto.product.dto.ProductUpdateDTO;
import com.proyecto.product.entity.Product;
import com.proyecto.product.mapper.ProductMapper;
import com.proyecto.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productoMapper;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> response = productoMapper.toDTOList(productRepository.findAll());
        return response;
    }

    public ProductDTO getProductById(Long id) {
        Product response = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(response);
    }

    public List<ProductDTO> addProductList(List<ProductCreateDTO> productos) {
        List<Product> listaProductos = productoMapper.toEntityList(productos);
        List<ProductDTO> response = productoMapper.toDTOList(productRepository.saveAll(listaProductos));
        return response;
    }

    public void productUpdate(Long id, ProductUpdateDTO dto) {
        Product productoExistente = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado"));

        productoMapper.updateProductFromDto(dto, productoExistente);
    }

    public void productDelete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }

}
