package com.proyecto;

import com.proyecto.DTOs.CartAddProductDTO;
import com.proyecto.DTOs.CartDTO;
import com.proyecto.DTOs.CartUpdateDTO;
import com.proyecto.exceptions.ResourceNotFoundException;
import com.proyecto.mappers.CartMapper;
import com.proyecto.models.*;
import com.proyecto.repositories.CartRepository;
import com.proyecto.repositories.ProductListingRepository;
import com.proyecto.repositories.UserRepository;
import com.proyecto.services.CartService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CartService - Tests unitarios")
@SuppressWarnings("null")
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductListingRepository productListingRepository;
    @Mock
    private CartMapper cartMapper;
    @Mock
    private ProductListingService productListingService; // delegado para validar stock

    @InjectMocks
    private CartService cartService;

    // ─── Fixtures reutilizables ───────────────────────────────────────────────

    private User user;
    private Cart cart;
    private CartProduct cartProduct;
    private ProductListing productListing;
    private CartDTO cartDTO;

    @BeforeEach
    void setUp() {
        productListing = new ProductListing();
        productListing.setId(1L);
        productListing.setPrice(new BigDecimal("100.00"));

        cartProduct = new CartProduct();
        cartProduct.setProductListing(productListing);
        cartProduct.setQuantity(2);

        cart = new Cart();
        cart.setProducts(new ArrayList<>(List.of(cartProduct)));
        cartProduct.setCart(cart);

        user = new User();
        user.setEmail("test@email.com");
        user.setCart(cart);

        cartDTO = new CartDTO(1L, List.of(), BigDecimal.ZERO);
    }

    // =========================================================================
    // getCartByEmail
    // =========================================================================

    @Nested
    @DisplayName("getCartByEmail()")
    class GetCartByEmailTests {

        @Test
        @DisplayName("Error: usuario no encontrado → lanza ResourceNotFoundException")
        void getCart_userNotFound_throwsException() {
            when(userRepository.findByEmail("noexiste@email.com"))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> cartService.getCartByEmail("noexiste@email.com"))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Usuario no encontrado");
        }

        @Test
        @DisplayName("Error: usuario sin carrito → lanza ResourceNotFoundException")
        void getCart_cartIsNull_throwsException() {
            user.setCart(null);
            when(userRepository.findByEmail(user.getEmail()))
                    .thenReturn(Optional.of(user));

            assertThatThrownBy(() -> cartService.getCartByEmail(user.getEmail()))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Carrito no encontrado");
        }

        @Test
        @DisplayName("Happy path: retorna el CartDTO correctamente")
        void getCart_success_returnsCartDTO() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            CartDTO result = cartService.getCartByEmail(user.getEmail());

            assertThat(result).isNotNull().isEqualTo(cartDTO);
            verify(cartMapper).toDTO(cart);
        }

        @Test
        @DisplayName("Edge case: carrito vacío → total = 0, no lanza excepción")
        void getCart_emptyCart_totalIsZero() {
            cart.setProducts(new ArrayList<>());
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            cartService.getCartByEmail(user.getEmail());

            assertThat(cart.getTotal()).isEqualByComparingTo(BigDecimal.ZERO);
        }

        @Test
        @DisplayName("Edge case: calcula subtotales y total correctamente")
        void getCart_calculatesTotalsCorrectly() {
            // producto 1: cantidad 2 × $100 = $200
            // producto 2: cantidad 3 × $50 = $150 → total = $350
            ProductListing pl2 = new ProductListing();
            pl2.setId(2L);
            pl2.setPrice(new BigDecimal("50.00"));

            CartProduct cp2 = new CartProduct();
            cp2.setProductListing(pl2);
            cp2.setQuantity(3);
            cp2.setCart(cart);

            cart.setProducts(new ArrayList<>(List.of(cartProduct, cp2)));

            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            cartService.getCartByEmail(user.getEmail());

            assertThat(cartProduct.getSubtotal()).isEqualByComparingTo("200.00");
            assertThat(cp2.getSubtotal()).isEqualByComparingTo("150.00");
            assertThat(cart.getTotal()).isEqualByComparingTo("350.00");
        }
    }

    // =========================================================================
    // updateCart
    // =========================================================================

    @Nested
    @DisplayName("updateCart()")
    class UpdateCartTests {

        private CartUpdateDTO buildDTO(Long productId, int quantity) {
            return new CartUpdateDTO(productId, quantity);
        }

        @Test
        @DisplayName("Error: usuario no encontrado → lanza ResourceNotFoundException")
        void updateCart_userNotFound_throwsException() {
            when(userRepository.findByEmail("noexiste@email.com"))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> cartService.updateCart("noexiste@email.com", buildDTO(1L, 1)))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Usuario no encontrado");
        }

        @Test
        @DisplayName("Error: producto no está en el carrito → lanza ResourceNotFoundException")
        void updateCart_productNotInCart_throwsException() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            assertThatThrownBy(() -> cartService.updateCart(user.getEmail(), buildDTO(99L, 1)))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Producto no encontrado");
        }

        @Test
        @DisplayName("Happy path: incrementa cantidad correctamente")
        void updateCart_incrementQuantity_success() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartRepository.save(cart)).thenReturn(cart);
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            // cantidad actual: 2 → le sumamos 3 → resultado: 5
            cartService.updateCart(user.getEmail(), buildDTO(1L, 3));

            assertThat(cartProduct.getQuantity()).isEqualTo(5);
            verify(cartRepository).save(cart);
        }

        @Test
        @DisplayName("Happy path: decrementa cantidad correctamente")
        void updateCart_decrementQuantity_success() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartRepository.save(cart)).thenReturn(cart);
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            // cantidad actual: 2 → le restamos 1 → resultado: 1
            cartService.updateCart(user.getEmail(), buildDTO(1L, -1));

            assertThat(cartProduct.getQuantity()).isEqualTo(1);
            verify(cartRepository).save(cart);
        }

        @Test
        @DisplayName("Edge case: nueva cantidad = 0 → producto eliminado del carrito")
        void updateCart_newQuantityIsZero_productRemoved() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartRepository.save(cart)).thenReturn(cart);
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            // cantidad actual: 2 → le restamos 2 → resultado: 0 → se elimina
            cartService.updateCart(user.getEmail(), buildDTO(1L, -2));

            assertThat(cart.getProducts()).doesNotContain(cartProduct);
        }

        @Test
        @DisplayName("Edge case: nueva cantidad negativa → producto eliminado del carrito")
        void updateCart_newQuantityIsNegative_productRemoved() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartRepository.save(cart)).thenReturn(cart);
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            // cantidad actual: 2 → le restamos 10 → resultado: -8 → se elimina
            cartService.updateCart(user.getEmail(), buildDTO(1L, -10));

            assertThat(cart.getProducts()).doesNotContain(cartProduct);
        }

        @Test
        @DisplayName("Edge case: carrito queda vacío después de eliminar el único producto")
        void updateCart_removeLastProduct_cartIsEmpty() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(cartRepository.save(cart)).thenReturn(cart);
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            cartService.updateCart(user.getEmail(), buildDTO(1L, -99));

            assertThat(cart.getProducts()).isEmpty();
        }
    }

    // =========================================================================
    // addProduct
    // =========================================================================

    @Nested
    @DisplayName("addProduct()")
    class AddProductTests {

        private CartAddProductDTO buildDTO(Long productId, int quantity) {
            return new CartAddProductDTO(productId, quantity);
        }

        @Test
        @DisplayName("Error: usuario no encontrado → lanza ResourceNotFoundException")
        void addProduct_userNotFound_throwsException() {
            when(userRepository.findByEmail("noexiste@email.com"))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> cartService.addProduct("noexiste@email.com", buildDTO(1L, 1)))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Usuario no encontrado");
        }

        @Test
        @DisplayName("Error: productListing no encontrado → lanza ResourceNotFoundException")
        void addProduct_productListingNotFound_throwsException() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(productListingRepository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> cartService.addProduct(user.getEmail(), buildDTO(99L, 1)))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Producto Venta no encontrado");
        }

        @Test
        @DisplayName("Error: stock insuficiente → checkStock lanza ResourceNotFoundException")
        void addProduct_insufficientStock_throwsException() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(productListingRepository.findById(1L)).thenReturn(Optional.of(productListing));
            // checkStock es void → usamos doThrow para simular stock insuficiente
            doThrow(new ResourceNotFoundException("Stock insuficiente"))
                    .when(productListingService).checkStock(1L, 5);

            assertThatThrownBy(() -> cartService.addProduct(user.getEmail(), buildDTO(1L, 5)))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Stock insuficiente");
        }

        @Test
        @DisplayName("Happy path: agrega un producto nuevo al carrito")
        void addProduct_newProduct_addedToCart() {
            ProductListing newListing = new ProductListing();
            newListing.setId(2L);
            newListing.setPrice(new BigDecimal("50.00"));

            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(productListingRepository.findById(2L)).thenReturn(Optional.of(newListing));
            // checkStock no lanza → stock suficiente (comportamiento por defecto de un mock
            // void)
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            int previousSize = cart.getProducts().size();
            cartService.addProduct(user.getEmail(), buildDTO(2L, 1));

            assertThat(cart.getProducts()).hasSize(previousSize + 1);
            assertThat(cart.getProducts())
                    .anyMatch(cp -> cp.getProductListing().getId().equals(2L) && cp.getQuantity() == 1);
            verify(cartRepository).save(cart);
        }

        @Test
        @DisplayName("Happy path: producto ya existe en el carrito → acumula cantidad")
        void addProduct_existingProduct_accumulatesQuantity() {
            // El carrito ya tiene productListing id=1 con quantity=2
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(productListingRepository.findById(1L)).thenReturn(Optional.of(productListing));
            // checkStock no lanza → stock suficiente
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            cartService.addProduct(user.getEmail(), buildDTO(1L, 3));

            // 2 + 3 = 5
            assertThat(cartProduct.getQuantity()).isEqualTo(5);
            assertThat(cart.getProducts()).hasSize(1); // no se agregó un item duplicado
            verify(cartRepository).save(cart);
        }

        @Test
        @DisplayName("Edge case: cantidad = 1 con stock exacto → no lanza excepción")
        void addProduct_exactStockLimit_succeeds() {
            when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
            when(productListingRepository.findById(1L)).thenReturn(Optional.of(productListing));
            // checkStock no lanza → stock suficiente
            when(cartMapper.toDTO(cart)).thenReturn(cartDTO);

            assertThatNoException()
                    .isThrownBy(() -> cartService.addProduct(user.getEmail(), buildDTO(1L, 1)));
        }
    }
}
