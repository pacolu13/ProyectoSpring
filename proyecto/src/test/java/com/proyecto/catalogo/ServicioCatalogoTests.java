package com.proyecto.catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import com.proyecto.catalogo.dataprovider.DataProvider;


@ExtendWith(MockitoExtension.class)
public class ServicioCatalogoTests {

    /*
    @Mock
    private RepoCatalogo repoCatalogo;

    @InjectMocks
    private ServicioCatalogo servicioCatalogo;

    // Primero vamos a testear las excepciones

    @Test
    public void obtenerCatalogoNullTest() {
        when(repoCatalogo.findAll()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogo());
    }

    @Test
    public void obtenerCatalogoVacioTest() {
        when(repoCatalogo.findAll()).thenReturn(List.of());
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogo());
    }

    @Test
    public void obtenerCatalogoTest() {

        CatalogoDTO catalogoDTO = new CatalogoDTO(1L, 1L, "Producto de prueba", "Categoría de prueba",
                BigDecimal.valueOf(100), 10);

        when(repoCatalogo.findAll()).thenReturn(List.of(DataProvider.catalogoMock()));
        List<CatalogoDTO> resultado = servicioCatalogo.obtenerCatalogo();
        assertFalse(resultado.isEmpty());
        assertEquals(catalogoDTO, resultado.get(0));
    }

    @Test
    public void obtenerCatalogoPorIdNullTest() {
        when(repoCatalogo.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> servicioCatalogo.obtenerCatalogoPorId(1L));
    }

    @Test
    public void obtenerCatalogoPorIdTest() {
        CatalogoDTO catalogoDTO = new CatalogoDTO(1L, 1L, "Producto de prueba", "Categoría de prueba",
                BigDecimal.valueOf(100), 10);
        when(repoCatalogo.findById(anyLong())).thenReturn(Optional.of(DataProvider.catalogoMock()));
        CatalogoDTO resultado = servicioCatalogo.obtenerCatalogoPorId(1L);
        assertEquals(catalogoDTO, resultado);
    }

    @Test
    public void obtenerCatalogoPorFiltroTest() {
        var catalogoEntidad = DataProvider.catalogoMock();

        // Importante: usar repoCatalogo
        when(repoCatalogo.findAll(any(Specification.class)))
                .thenReturn(List.of(catalogoEntidad));

        // 3. Ejecutar el método del SERVICIO
        List<CatalogoDTO> resultado = servicioCatalogo.obtenerCatalogoPorFiltro(
                "Coca Cola", "Bebidas", BigDecimal.valueOf(10.0), BigDecimal.valueOf(50.0));

        // 4. Verificar
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(catalogoEntidad.getId(), resultado.get(0).getId());
    }
     */
}
