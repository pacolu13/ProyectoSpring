package com.proyecto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.ProductListingCreateDTO;
import com.proyecto.DTOs.ProductListingDTO;
import com.proyecto.DTOs.ProductListingSellDTO;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.mappers.ProductListingMapper;
import com.proyecto.models.Product;
import com.proyecto.models.ProductListing;
import com.proyecto.models.User;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.ProductRepository;
import com.proyecto.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@SuppressWarnings("null")
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
public class ProductListingService {

        private final UserRepository userRepository;
        private final ProductRepository productRepository;
        private final ProductListingRepository productListingRepository;
        private final ProductListingMapper productListingMapper;

        public List<ProductListingDTO> findAllProductsListing(Long idProduct) {
                List<ProductListing> productosVentaList = productListingRepository
                                .findAll()
                                .stream()
                                .filter(prdct -> prdct.getProduct().getId().equals(idProduct))
                                .collect(Collectors.toList());
                return productListingMapper.toDTOList(productosVentaList);
        }

        public ProductListingDTO findyProductListingById(Long id) {
                ProductListing prod = productListingRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Producto Venta no encontrado"));
                return productListingMapper.toDTO(prod);
        }

        public ProductListingDTO addProductListing(ProductListingCreateDTO productListing, String email) {
                User seller = userRepository.findByEmail(email)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Vendedor no encontrado: " + email));

                System.out.println(productListing);
                System.out.println(email);
                
                ProductListing productsListingSave = productListingMapper.toEntity(productListing);

                Product product = productsListingSave.getProduct();
                Product productSaved = productRepository.save(product);
                productsListingSave.setUser(seller);
                productsListingSave.setProduct(productSaved);
                seller.getProductsListingList().add(productsListingSave);

                ProductListing productsListingSaved = productListingRepository.save(productsListingSave);
                return productListingMapper.toDTO(productsListingSaved);
        }

        public List<ProductListingDTO> findAll() {
                List<ProductListing> productListingList = productListingRepository.findAll();
                return productListingMapper.toDTOList(productListingList);
        }

        public List<ProductListingSellDTO> findAllSellerListings(String email) {
            User seller = userRepository.findByEmail(email)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                            "Vendedor no encontrado: " + email));
                List<ProductListing> sellerListings = productListingRepository.findByUser(seller);
                return productListingMapper.toSellDTOList(sellerListings);
        }
}
