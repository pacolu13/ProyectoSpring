package com.proyecto.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.ProductCreateDTO;
import com.proyecto.DTOs.ProductDTO;
import com.proyecto.DTOs.ProductUpdateDTO;
import com.proyecto.config.ExceptionFactory;
import com.proyecto.mappers.ProductMapper;
import com.proyecto.models.Product;
import com.proyecto.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> response = productMapper.toDTOList(productRepository.findAll());
        return response;
    }

    public ProductDTO getProductById(Long id) {
        Product response = productRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.createProductNotFoundException());
        return productMapper.toDTO(response);
    }

    public List<ProductDTO> addProductList(List<ProductCreateDTO> productCreateDTOs) {
        List<Product> productCreatelistDTOs = productMapper.toEntityList(productCreateDTOs);
        List<ProductDTO> response = productMapper.toDTOList(productRepository.saveAll(productCreatelistDTOs));
        return response;
    }

    public void productUpdate(Long id, ProductUpdateDTO dto) {
        Product productUpdate = productRepository.findById(id).orElseThrow(
                () -> ExceptionFactory.createProductNotFoundException());

        productMapper.updateProductFromDto(dto, productUpdate);
    }

    public void productDelete(Long id) {
        if (!productRepository.existsById(id)) {
            throw ExceptionFactory.createProductNotFoundException();
        }
        productRepository.deleteById(id);
    }

}
