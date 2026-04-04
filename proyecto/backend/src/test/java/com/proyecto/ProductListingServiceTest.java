package com.proyecto;

import com.proyecto.DTOs.ProductDTO;
import com.proyecto.DTOs.ProductListingCreateDTO;
import com.proyecto.DTOs.ProductListingDTO;
import com.proyecto.DTOs.SellerProductListingDTO;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.mappers.ProductListingMapper;
import com.proyecto.models.Product;
import com.proyecto.models.ProductListing;
import com.proyecto.models.StateProduct;
import com.proyecto.models.User;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.ProductRepository;
import com.proyecto.repositories.UserRepository;
import com.proyecto.services.ProductListingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductListingService - Unit Tests")
class ProductListingServiceTest {

    // =========================================================================
    // Mocks & Subject Under Test
    // =========================================================================

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductListingRepository productListingRepository;
    @Mock
    private ProductListingMapper productListingMapper;

    @InjectMocks
    private ProductListingService productListingService;

    // =========================================================================
    // Fixtures comunes
    // =========================================================================

    private User seller;
    private Product product;
    private ProductListing productListing;
    private ProductListingDTO productListingDTO;

    /**
     * BUENA PRÁCTICA: @BeforeEach para construir fixtures limpios antes de cada
     * test.
     * Evita estado compartido entre tests (un test no debe afectar a otro).
     */
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);

        seller = new User();
        seller.setId(UUID.randomUUID());
        seller.setEmail("seller@example.com");
        seller.setProductsListings(new ArrayList<>());

        productListing = new ProductListing();
        productListing.setId(100L);
        productListing.setProduct(product);
        productListing.setUser(seller);
        productListing.setStock(10);

        // Los DTOs son records, se instancian directamente en cada test según lo que
        // necesitemos.
        LocalDateTime fixedDate = LocalDateTime.of(2024, 1, 15, 10, 0, 0);
        ProductDTO productDTO = new ProductDTO(1L,
                "Name",
                "description",
                "Brand",
                "Category",
                "Image", true,
                fixedDate);
        SellerProductListingDTO sellerDTO = new SellerProductListingDTO(
                "sellerUser",
                50,
                4.5f,
                "City");

        productListingDTO = new ProductListingDTO(
                1L,
                "Title",
                "Description",
                BigDecimal.valueOf(10.00),
                10,
                StateProduct.NEW,
                fixedDate,
                productDTO,
                sellerDTO);
    }

    // =========================================================================
    // findAllProductsListing
    // =========================================================================

    @Nested
    @DisplayName("findAllProductsListing()")
    class FindAllProductsListingTests {

        /**
         * BUENA PRÁCTICA: nombre del test describe SITUACIÓN → ACCIÓN → RESULTADO
         * esperado.
         * Formato: methodName_givenCondition_shouldExpectedBehavior
         */
        @Test
        @DisplayName("Dado listings existentes, filtra correctamente por idProduct")
        void findAllProductsListing_givenListingsForProduct_shouldReturnOnlyMatchingOnes() {
            // Arrange
            Long targetProductId = 1L;

            Product otherProduct = new Product();
            otherProduct.setId(99L);

            ProductListing otherListing = new ProductListing();
            otherListing.setProduct(otherProduct);

            List<ProductListing> allListings = List.of(productListing, otherListing);

            when(productListingRepository.findAll()).thenReturn(allListings);
            when(productListingMapper.toDTOList(List.of(productListing)))
                    .thenReturn(List.of(productListingDTO));

            // Act
            List<ProductListingDTO> result = productListingService.findAllProductsListing(targetProductId);

            // Assert
            assertThat(result).hasSize(1).containsExactly(productListingDTO);
            verify(productListingRepository).findAll();
            verify(productListingMapper).toDTOList(List.of(productListing));
        }

        @Test
        @DisplayName("Dado que no hay listings para el producto, retorna lista vacía")
        void findAllProductsListing_givenNoMatchingListings_shouldReturnEmptyList() {
            // Arrange
            when(productListingRepository.findAll()).thenReturn(List.of(productListing));
            when(productListingMapper.toDTOList(List.of())).thenReturn(List.of());

            // Act
            List<ProductListingDTO> result = productListingService.findAllProductsListing(999L);

            // Assert
            assertThat(result).isEmpty();
        }
    }

    // =========================================================================
    // findyProductListingById
    // =========================================================================

    @Nested
    @DisplayName("findyProductListingById()")
    class FindProductListingByIdTests {

        @Test
        @DisplayName("Dado un id existente, retorna el DTO correspondiente")
        void findyProductListingById_givenExistingId_shouldReturnDTO() {
            // Arrange
            when(productListingRepository.findById(100L)).thenReturn(Optional.of(productListing));
            when(productListingMapper.toDTO(productListing)).thenReturn(productListingDTO);

            // Act
            ProductListingDTO result = productListingService.findyProductListingById(100L);

            // Assert
            assertThat(result).isEqualTo(productListingDTO);
            verify(productListingRepository).findById(100L);
        }

        @Test
        @DisplayName("Dado un id inexistente, lanza ResourceNotFoundException")
        void findyProductListingById_givenNonExistingId_shouldThrowResourceNotFoundException() {
            // Arrange
            when(productListingRepository.findById(any())).thenReturn(Optional.empty());

            // Act & Assert
            /**
             * BUENA PRÁCTICA: assertThatThrownBy agrupa la acción y la aserción
             * en un bloque fluido. Evita el patrón try/catch manual.
             */
            assertThatThrownBy(() -> productListingService.findyProductListingById(999L))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Product listing not found");

            // Verificamos que no se llamó al mapper (el flujo cortó antes)
            verifyNoInteractions(productListingMapper);
        }
    }

    // =========================================================================
    // addProductListing
    // =========================================================================

    @Nested
    @DisplayName("addProductListing()")
    class AddProductListingTests {

        private ProductListingCreateDTO createDTO;

        @BeforeEach
        void setUpCreateDTO() {
            createDTO = new ProductListingCreateDTO(
                    "Title",
                    "Description",
                    BigDecimal.valueOf(10.00),
                    10,
                    StateProduct.NEW,
                    new ProductDTO(1L,
                            "Name",
                            "description",
                            "Brand",
                            "Category",
                            "Image", true,
                            LocalDateTime.now()));
        }

        @Test
        @DisplayName("Dado vendedor y producto válidos, persiste y retorna el DTO creado")
        void addProductListing_givenValidSellerAndProduct_shouldSaveAndReturnDTO() {
            // Arrange
            String email = seller.getEmail();

            when(userRepository.findByEmail(email)).thenReturn(Optional.of(seller));
            when(productListingMapper.toEntity(createDTO)).thenReturn(productListing);
            when(productRepository.save(product)).thenReturn(product);
            when(productListingRepository.save(productListing)).thenReturn(productListing);
            when(productListingMapper.toDTO(productListing)).thenReturn(productListingDTO);

            // Act
            ProductListingDTO result = productListingService.addProductListing(createDTO, email);

            // Assert
            assertThat(result).isEqualTo(productListingDTO);

            /**
             * BUENA PRÁCTICA: verificar interacciones con dependencias críticas.
             * Nos aseguramos de que el producto fue guardado ANTES que el listing.
             */
            verify(productRepository).save(product);
            verify(productListingRepository).save(productListing);

            // El seller debe tener el listing agregado a su colección
            assertThat(seller.getProductsListings()).contains(productListing);
        }

        @Test
        @DisplayName("Dado un email de vendedor inexistente, lanza ResourceNotFoundException")
        void addProductListing_givenNonExistingSeller_shouldThrowResourceNotFoundException() {
            // Arrange
            String unknownEmail = "ghost@nowhere.com";
            when(userRepository.findByEmail(unknownEmail)).thenReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> productListingService.addProductListing(createDTO, unknownEmail))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Seller not found");

            // Si el seller no existe, nunca debemos guardar nada
            verifyNoInteractions(productRepository, productListingRepository);
        }
    }

    // =========================================================================
    // findAll
    // =========================================================================

    @Nested
    @DisplayName("findAll()")
    class FindAllTests {

        @Test
        @DisplayName("Retorna todos los listings mapeados a DTO")
        void findAll_shouldReturnAllListingsMappedToDTO() {
            // Arrange
            List<ProductListing> listings = List.of(productListing);
            when(productListingRepository.findAll()).thenReturn(listings);
            when(productListingMapper.toDTOList(listings)).thenReturn(List.of(productListingDTO));

            // Act
            List<ProductListingDTO> result = productListingService.findAll();

            // Assert
            assertThat(result).containsExactly(productListingDTO);
        }

        @Test
        @DisplayName("Dado que no hay listings, retorna lista vacía")
        void findAll_givenNoListings_shouldReturnEmptyList() {
            // Arrange
            when(productListingRepository.findAll()).thenReturn(List.of());
            when(productListingMapper.toDTOList(List.of())).thenReturn(List.of());

            // Act
            List<ProductListingDTO> result = productListingService.findAll();

            // Assert
            assertThat(result).isEmpty();
        }
    }

    // =========================================================================
    // findAllSellerListings
    // =========================================================================

    @Nested
    @DisplayName("findAllSellerListings()")
    class FindAllSellerListingsTests {

        @Test
        @DisplayName("Dado un email de vendedor existente, retorna sus listings")
        void findAllSellerListings_givenExistingSeller_shouldReturnTheirListings() {
            // Arrange
            String email = seller.getEmail();
            List<ProductListing> sellerListings = List.of(productListing);

            when(userRepository.findByEmail(email)).thenReturn(Optional.of(seller));
            when(productListingRepository.findByUser(seller)).thenReturn(sellerListings);
            when(productListingMapper.toDTOList(sellerListings)).thenReturn(List.of(productListingDTO));

            // Act
            List<ProductListingDTO> result = productListingService.findAllSellerListings(email);

            // Assert
            assertThat(result).containsExactly(productListingDTO);
            verify(productListingRepository).findByUser(seller);
        }

        @Test
        @DisplayName("Dado un email inexistente, lanza ResourceNotFoundException")
        void findAllSellerListings_givenNonExistingSeller_shouldThrowResourceNotFoundException() {
            // Arrange
            String unknownEmail = "nobody@store.com";
            when(userRepository.findByEmail(unknownEmail)).thenReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> productListingService.findAllSellerListings(unknownEmail))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Seller not found");

            verifyNoInteractions(productListingRepository, productListingMapper);
        }
    }

    // =========================================================================
    // checkStock
    // =========================================================================

    @Nested
    @DisplayName("checkStock()")
    class CheckStockTests {

        @Test
        @DisplayName("Dado stock suficiente, retorna true")
        void checkStock_givenSufficientStock_shouldReturnTrue() {
            // Arrange — stock es 10 (ver @BeforeEach)
            when(productListingRepository.findById(100L)).thenReturn(Optional.of(productListing));

            // Act
            boolean result = productListingService.checkStock(100L, 5);

            // Assert
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Dado stock exactamente igual a la cantidad pedida, retorna true (borde)")
        void checkStock_givenStockEqualToQuantity_shouldReturnTrue() {
            // Arrange
            when(productListingRepository.findById(100L)).thenReturn(Optional.of(productListing));

            // Act
            boolean result = productListingService.checkStock(100L, 10);

            // Assert — valor límite: >= debe incluir la igualdad
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Dado stock insuficiente, retorna false")
        void checkStock_givenInsufficientStock_shouldReturnFalse() {
            // Arrange
            when(productListingRepository.findById(100L)).thenReturn(Optional.of(productListing));

            // Act
            boolean result = productListingService.checkStock(100L, 11);

            // Assert
            assertThat(result).isFalse();
        }

        @Test
        @DisplayName("Dado un id de listing inexistente, lanza ResourceNotFoundException")
        void checkStock_givenNonExistingProductListing_shouldThrowResourceNotFoundException() {
            // Arrange
            when(productListingRepository.findById(any())).thenReturn(Optional.empty());

            // Act & Assert
            assertThatThrownBy(() -> productListingService.checkStock(999L, 1))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Product listing not found");
        }
    }
}
