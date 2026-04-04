package com.proyecto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.DTOs.ProductCreateDTO;
import com.proyecto.DTOs.ProductDTO;
import com.proyecto.DTOs.ProductUpdateDTO;
import com.proyecto.mappers.ProductMapper;
import com.proyecto.models.Product;
import com.proyecto.repositories.ProductRepository;
import com.proyecto.services.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repoProducto;

    @Mock
    private ProductMapper productoMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDTO productDTO;
    private ProductCreateDTO productCreateDTO;
    private ProductUpdateDTO productUpdateDTO;

    @BeforeEach
    void setUp() {
    product = new Product();
    product.setId(1L);
    product.setName("Laptop");
    product.setDescription("A high-end gaming laptop");
    product.setCategory("Electronics");
    product.setBrand("BrandX");
    product.setImage("image.jpg");
    product.setActive(true);
    product.setCreationDate(LocalDateTime.now());

    productDTO = new ProductDTO(1L, "Laptop", "A high-end gaming laptop", "BrandX", "Electronics", "image.jpg", true, LocalDateTime.now());
    }

    // ─────────────────────────────────────────────
    // obtenerTodosLosProductos
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("obtenerTodosLosProductos - retorna lista de ProductDTO")
    void obtenerTodosLosProductos_RetornaLista() {
        List<Product> products = Arrays.asList(product);
        List<ProductDTO> productDTOs = Arrays.asList(productDTO);

        when(repoProducto.findAll()).thenReturn(products);
        when(productoMapper.toDTOList(products)).thenReturn(productDTOs);

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productDTOs, result);

        verify(repoProducto, times(1)).findAll();
        verify(productoMapper, times(1)).toDTOList(products);
    }

    @Test
    @DisplayName("obtenerTodosLosProductos - retorna lista vacía cuando no hay productos")
    void obtenerTodosLosProductos_ListaVacia() {
        when(repoProducto.findAll()).thenReturn(Collections.emptyList());
        when(productoMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ProductDTO> result = productService.getAllProducts();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repoProducto, times(1)).findAll();
    }

    // ─────────────────────────────────────────────
    // obtenerProductoPorId
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("obtenerProductoPorId - retorna ProductDTO cuando existe")
    void obtenerProductoPorId_CuandoExiste_RetornaDTO() {
        when(repoProducto.findById(1L)).thenReturn(Optional.of(product));
        when(productoMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(productDTO, result);
        verify(repoProducto, times(1)).findById(1L);
        verify(productoMapper, times(1)).toDTO(product);
    }

    @Test
    @DisplayName("obtenerProductoPorId - lanza RuntimeException cuando no existe")
    void obtenerProductoPorId_CuandoNoExiste_LanzaExcepcion() {
        when(repoProducto.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.getProductById(99L));

        assertEquals("Product not found", ex.getMessage());
        verify(repoProducto, times(1)).findById(99L);
        verify(productoMapper, never()).toDTO(any());
    }

    // ─────────────────────────────────────────────
    // añadirListaProducto
    // ─────────────────────────────────────────────

    @SuppressWarnings("null")
    @Test
    @DisplayName("añadirListaProducto - guarda y retorna lista de ProductDTO")
    void añadirListaProducto_GuardaYRetornaLista() {
        List<ProductCreateDTO> createDTOs = Arrays.asList(productCreateDTO);
        List<Product> entities = Arrays.asList(product);
        List<ProductDTO> savedDTOs = Arrays.asList(productDTO);

        when(productoMapper.toEntityList(createDTOs)).thenReturn(entities);
        when(repoProducto.saveAll(entities)).thenReturn(entities);
        when(productoMapper.toDTOList(entities)).thenReturn(savedDTOs);

        List<ProductDTO> result = productService.addProductList(createDTOs);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(savedDTOs, result);

        verify(productoMapper, times(1)).toEntityList(createDTOs);
        verify(repoProducto, times(1)).saveAll(entities);
        verify(productoMapper, times(1)).toDTOList(entities);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("añadirListaProducto - lista vacía no llama a saveAll con elementos")
    void añadirListaProducto_ListaVacia() {
        when(productoMapper.toEntityList(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(repoProducto.saveAll(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(productoMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<ProductDTO> result = productService.addProductList(Collections.emptyList());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // ─────────────────────────────────────────────
    // actualizarProducto
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("actualizarProducto - actualiza cuando el producto existe")
    void actualizarProducto_CuandoExiste_Actualiza() {
        when(repoProducto.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productoMapper).updateProductFromDto(productUpdateDTO, product);

        assertDoesNotThrow(() -> productService.productUpdate(1L, productUpdateDTO));

        verify(repoProducto, times(1)).findById(1L);
        verify(productoMapper, times(1)).updateProductFromDto(productUpdateDTO, product);
    }

    @Test
    @DisplayName("actualizarProducto - lanza RuntimeException cuando no existe")
    void actualizarProducto_CuandoNoExiste_LanzaExcepcion() {
        when(repoProducto.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.productUpdate(99L, productUpdateDTO));

        assertEquals("Product not found", ex.getMessage());
        verify(productoMapper, never()).updateProductFromDto(any(), any());
    }

    // ─────────────────────────────────────────────
    // eliminarProducto
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("eliminarProducto - elimina cuando el producto existe")
    void eliminarProducto_CuandoExiste_Elimina() {
        when(repoProducto.existsById(1L)).thenReturn(true);
        doNothing().when(repoProducto).deleteById(1L);

        assertDoesNotThrow(() -> productService.productDelete(1L));

        verify(repoProducto, times(1)).existsById(1L);
        verify(repoProducto, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("eliminarProducto - lanza RuntimeException cuando no existe")
    void eliminarProducto_CuandoNoExiste_LanzaExcepcion() {
        when(repoProducto.existsById(anyLong())).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productService.productDelete(99L));

        assertEquals("Product not found", ex.getMessage());
        verify(repoProducto, never()).deleteById(anyLong());
    }

    
}