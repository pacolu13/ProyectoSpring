package com.proyecto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.DTOs.ProductListingCreateDTO;
import com.proyecto.DTOs.ProductListingDTO;
import com.proyecto.DTOs.ProductListingUpdateDTO;
import com.proyecto.config.ExceptionFactory;
import com.proyecto.mappers.ProductListingMapper;
import com.proyecto.models.Product;
import com.proyecto.models.ProductListing;
import com.proyecto.models.User;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.ProductRepository;
import com.proyecto.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Agrega esta anotación para generar el constructor con los campos finales
@SuppressWarnings("null")
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
                ProductListing prod = getProductListingById(id);
                return productListingMapper.toDTO(prod);
        }

        public ProductListingDTO addProductListing(ProductListingCreateDTO productListing, String email) {
                User seller = getSellerByEmail(email);
                ProductListing productsListingSave = productListingMapper.toEntity(productListing);

                Product product = productsListingSave.getProduct();
                Product productSaved = productRepository.save(product);
                productsListingSave.setUser(seller);
                productsListingSave.setProduct(productSaved);
                seller.getProductsListings().add(productsListingSave);

                ProductListing productsListingSaved = productListingRepository.save(productsListingSave);
                return productListingMapper.toDTO(productsListingSaved);
        }

        public List<ProductListingDTO> findAll() {
                List<ProductListing> productListingList = productListingRepository.findAll();
                return productListingMapper.toDTOList(productListingList);
        }

        public List<ProductListingDTO> findAllSellerListings(String email) {
                User seller = getSellerByEmail(email);
                List<ProductListing> sellerListings = productListingRepository.findByUser(seller);
                return productListingMapper.toDTOList(sellerListings);
        }

        public ProductListingDTO updateProductListing(Long idListing, ProductListingUpdateDTO productListing,
                        String email) {
                ProductListing existingListing = getProductListingById(idListing);

                if (!existingListing.getUser().getEmail().equals(email)) {
                        throw new SecurityException("Unauthorized");
                }

                ProductListing updatedEntity = productListingMapper.updateProductFromDto(productListing,
                                existingListing);
                ProductListing updatedListing = productListingRepository.save(updatedEntity);
                return productListingMapper.toDTO(updatedListing);

        }

        public void deleteProductListing(Long idListing, String email) {
                ProductListing existingListing = getProductListingById(idListing);

                if (!existingListing.getUser().getEmail().equals(email)) {
                        throw new SecurityException("Unauthorized");
                }

                productListingRepository.delete(existingListing);
        }

        public boolean checkStock(Long id, Integer quantity) {
                ProductListing prod = getProductListingById(id);
                return prod.getStock() >= quantity;
        }

        private ProductListing getProductListingById(Long id) {
                return productListingRepository.findById(id)
                                .orElseThrow(() -> ExceptionFactory.createProductListingNotFoundException());
        }

        private User getSellerByEmail(String email) {
                return userRepository.findByEmail(email)
                                .orElseThrow(() -> ExceptionFactory.createSellerNotFoundException());
        }

}
