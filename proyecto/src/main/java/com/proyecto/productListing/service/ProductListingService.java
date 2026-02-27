package com.proyecto.productListing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.product.entity.Product;
import com.proyecto.product.repository.ProductRepository;
import com.proyecto.productListing.dto.ProductListingCreateDTO;
import com.proyecto.productListing.dto.ProductListingDTO;
import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.productListing.mapper.ProductListingMapper;
import com.proyecto.productListing.repository.ProductListingRepository;
import com.proyecto.seller.entity.Seller;
import com.proyecto.seller.repository.SellerRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
public class ProductListingService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductListingRepository productListingRepository;
    private final ProductListingMapper productListingMapper;

    public List<ProductListingDTO> findAllProductsListing() {
        List<ProductListing> productosVentaList = productListingRepository.findAll();
        return productListingMapper.toDTOList(productosVentaList);
    }

    public ProductListingDTO findyProductListingById(Long id) {
        ProductListing prod = productListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));
        return productListingMapper.toDTO(prod);
    }

    public List<ProductListingDTO> addProductListingList(List<ProductListingCreateDTO> productsListingList) {
        List<ProductListing> productsListingSave = productsListingList.stream()
                .map(dto -> {
                    Product product = productRepository.findById(dto.productId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Producto no encontrado: " + dto.productId()));
                    Seller seller = sellerRepository.findById(dto.sellerId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Vendedor no encontrado: " + dto.sellerId()));

                    ProductListing entity = productListingMapper.toEntity(dto);
                    entity.setProduct(product);
                    entity.setSeller(seller);
                    return entity;
                })
                .toList();

        List<ProductListing> productsListingSaved = productListingRepository.saveAll(productsListingSave);
        return productListingMapper.toDTOList(productsListingSaved);
    }
}
