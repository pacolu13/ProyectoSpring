package com.proyecto.productListing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.product.entity.Product;
import com.proyecto.product.repository.ProductRepository;
import com.proyecto.productListing.dto.ProductListingCreateDTO;
import com.proyecto.productListing.dto.ProductListingDTO;
import com.proyecto.productListing.entity.ProductListing;
import com.proyecto.productListing.mapper.ProductListingMapper;
import com.proyecto.productListing.repository.ProductListingRepository;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

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
}
